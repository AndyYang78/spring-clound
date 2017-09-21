package com.eryu.common.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 解析转码后的emoji
 * <p>
 * 1、如果数据库已对emoji转码，可在取数据时、对应要映射的实体中含有emoji符号的setter里执行编码反转
 * 例：
 * public void setNickname(String nickname) {
 * this.nickname = EmojiUtil.translate(nickname);
 * }
 * 2、其他情况按需调用即可
 * Created by yangtao on 2017/5/26.
 */
public class EmojiUtil {

    private static final CharSequenceTranslator UNESCAPE_JAVA =
            new AggregateTranslator(
                    new OctalUnescaper(),     // .between('\1', '\377'),
                    new UnicodeUnescaper(),
                    new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_UNESCAPE()),
                    new LookupTranslator(
                            new String[][]{
                                    {"\\\\", "\\"},
                                    {"\\\"", "\""},
                                    {"\\'", "'"},
                                    {"\\", ""}
                            })
            );

    public static String translate(String input) {
        //扩展解码容错
        if (input != null)
            input = input.replace("\\\\", "\\");
        return UNESCAPE_JAVA.translate(input);
    }

    public static abstract class CharSequenceTranslator {

        public abstract int translate(CharSequence input, int index, Writer out) throws IOException;

        final String translate(final CharSequence input) {
            if (input == null) {
                return null;
            }
            try {
                final StringWriter writer = new StringWriter(input.length() * 2);
                translate(input, writer);
                return writer.toString();
            } catch (final IOException ioe) {
                // this should never ever happen while writing to a StringWriter
                throw new RuntimeException(ioe);
            }
        }

        final void translate(final CharSequence input, final Writer out) throws IOException {
            if (out == null) {
                throw new IllegalArgumentException("The Writer must not be null");
            }
            if (input == null) {
                return;
            }
            int pos = 0;
            final int len = input.length();
            while (pos < len) {
                final int consumed = translate(input, pos, out);
                if (consumed == 0) {
                    // inlined implementation of Character.toChars(Character.codePointAt(input, pos))
                    // avoids allocating temp char arrays and duplicate checks
                    char c1 = input.charAt(pos);
                    out.write(c1);
                    pos++;
                    if (Character.isHighSurrogate(c1) && pos < len) {
                        char c2 = input.charAt(pos);
                        if (Character.isLowSurrogate(c2)) {
                            out.write(c2);
                            pos++;
                        }
                    }
                    continue;
                }
                // contract with translators is that they have to understand codepoints
                // and they just took care of a surrogate pair
                for (int pt = 0; pt < consumed; pt++) {
                    pos += Character.charCount(Character.codePointAt(input, pos));
                }
            }
        }

    }

    public static class AggregateTranslator extends CharSequenceTranslator {

        private final CharSequenceTranslator[] translators;

        AggregateTranslator(final CharSequenceTranslator... translators) {
            this.translators = translators == null ? null : translators.clone();
        }

        @Override
        public int translate(final CharSequence input, final int index, final Writer out) throws IOException {
            for (final CharSequenceTranslator translator : translators) {
                final int consumed = translator.translate(input, index, out);
                if (consumed != 0) {
                    return consumed;
                }
            }
            return 0;
        }

    }

    public static class OctalUnescaper extends CharSequenceTranslator {

        @Override
        public int translate(final CharSequence input, final int index, final Writer out) throws IOException {
            final int remaining = input.length() - index - 1; // how many characters left, ignoring the first \
            final StringBuilder builder = new StringBuilder();
            if (input.charAt(index) == '\\' && remaining > 0 && isOctalDigit(input.charAt(index + 1))) {
                final int next = index + 1;
                final int next2 = index + 2;
                final int next3 = index + 3;

                // we know this is good as we checked it in the if block above
                builder.append(input.charAt(next));

                if (remaining > 1 && isOctalDigit(input.charAt(next2))) {
                    builder.append(input.charAt(next2));
                    if (remaining > 2 && isZeroToThree(input.charAt(next)) && isOctalDigit(input.charAt(next3))) {
                        builder.append(input.charAt(next3));
                    }
                }

                out.write(Integer.parseInt(builder.toString(), 8));
                return 1 + builder.length();
            }
            return 0;
        }

        private boolean isOctalDigit(final char ch) {
            return ch >= '0' && ch <= '7';
        }

        private boolean isZeroToThree(final char ch) {
            return ch >= '0' && ch <= '3';
        }
    }

    public static class UnicodeUnescaper extends CharSequenceTranslator {

        @Override
        public int translate(final CharSequence input, final int index, final Writer out) throws IOException {
            if (input.charAt(index) == '\\' && index + 1 < input.length() && input.charAt(index + 1) == 'u') {
                // consume optional additional 'u' chars
                int i = 2;
                while (index + i < input.length() && input.charAt(index + i) == 'u') {
                    i++;
                }

                if (index + i < input.length() && input.charAt(index + i) == '+') {
                    i++;
                }

                if (index + i + 4 <= input.length()) {
                    // Get 4 hex digits
                    final CharSequence unicode = input.subSequence(index + i, index + i + 4);

                    try {
                        final int value = Integer.parseInt(unicode.toString(), 16);
                        out.write((char) value);
                    } catch (final NumberFormatException nfe) {
                        throw new IllegalArgumentException("Unable to parse unicode value: " + unicode, nfe);
                    }
                    return i + 4;
                }
                throw new IllegalArgumentException("Less than 4 hex digits in unicode value: '" + input.subSequence(index, input.length()) + "' due to end of CharSequence");
            }
            return 0;
        }
    }

    /**
     * Translates a value using a lookup table.
     */
    public static class LookupTranslator extends CharSequenceTranslator {

        private final HashMap<String, String> lookupMap;
        private final HashSet<Character> prefixSet;
        private final int shortest;
        private final int longest;

        LookupTranslator(final CharSequence[]... lookup) {
            lookupMap = new HashMap<>();
            prefixSet = new HashSet<>();
            int _shortest = Integer.MAX_VALUE;
            int _longest = 0;
            if (lookup != null) {
                for (final CharSequence[] seq : lookup) {
                    this.lookupMap.put(seq[0].toString(), seq[1].toString());
                    this.prefixSet.add(seq[0].charAt(0));
                    final int sz = seq[0].length();
                    if (sz < _shortest) {
                        _shortest = sz;
                    }
                    if (sz > _longest) {
                        _longest = sz;
                    }
                }
            }
            shortest = _shortest;
            longest = _longest;
        }

        @Override
        public int translate(final CharSequence input, final int index, final Writer out) throws IOException {
            // check if translation exists for the input at position index
            if (prefixSet.contains(input.charAt(index))) {
                int max = longest;
                if (index + longest > input.length()) {
                    max = input.length() - index;
                }
                // implement greedy algorithm by trying maximum match first
                for (int i = max; i >= shortest; i--) {
                    final CharSequence subSeq = input.subSequence(index, index + i);
                    final String result = lookupMap.get(subSeq.toString());

                    if (result != null) {
                        out.write(result);
                        return i;
                    }
                }
            }
            return 0;
        }
    }

    public static class EntityArrays {

        private static final String[][] JAVA_CTRL_CHARS_ESCAPE = {
                {"\b", "\\b"},
                {"\n", "\\n"},
                {"\t", "\\t"},
                {"\f", "\\f"},
                {"\r", "\\r"}
        };

        static String[][] JAVA_CTRL_CHARS_UNESCAPE() {
            return JAVA_CTRL_CHARS_UNESCAPE.clone();
        }

        private static final String[][] JAVA_CTRL_CHARS_UNESCAPE = invert(JAVA_CTRL_CHARS_ESCAPE);

        static String[][] invert(final String[][] array) {
            final String[][] newarray = new String[array.length][2];
            for (int i = 0; i < array.length; i++) {
                newarray[i][0] = array[i][1];
                newarray[i][1] = array[i][0];
            }
            return newarray;
        }

    }

}
