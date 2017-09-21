package com.eryu.common.helper;

import com.eryu.exception.ParameterException;
import org.springframework.util.StringUtils;

/**
 * 通用参数检查工具
 * Created by yangtao on 2017/6/29.
 */
public interface ParamHelper {

    boolean SUCCESS = true;

    /**
     * 不能为空字符串
     *
     * @param param        字符串参数
     * @param errorMessage 错误提示
     */
    default void empty(String param, String errorMessage) {
        if (StringUtils.isEmpty(param))
            throw new ParameterException(500, errorMessage);
    }

    /**
     * 参数不能为空
     *
     * @param param        字符串参数
     * @param errorMessage 错误提示
     */
    default void empty(Object param, String errorMessage) {
        if (null == param)
            throw new ParameterException(500, errorMessage);
    }
}
