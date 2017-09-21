package com.eryu.common.helper;

/**
 * ...
 * Created by yangtao on 2017/7/26.
 */
public interface MapHelper {

    /**
     * 获取指定长度的HashMap的实际初始长度（避免 resize() 带来的性能消耗）
     *
     * @param expectedSize 指定的长度
     * @return 实际应该初始化的长度
     */
    default int capacity(int expectedSize) {
        if (expectedSize < 3) {
            return expectedSize + 1;
        }
        if (expectedSize < (1 << (Integer.SIZE - 2))) {
            return (int) ((float) expectedSize / 0.75F + 1.0F);
        }
        return Integer.MAX_VALUE;
    }
}
