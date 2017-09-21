package com.eryu.common.helper;

import com.eryu.dto.SimpleResponse;
import com.eryu.exception.BusinessException;

/**
 * 响应处理工具
 * Created by yangtao on 2017/8/3.
 */
public interface ResponseHelper {

    default void handleResponse(SimpleResponse response) {
        if (!response.getSuccess())
            throw new BusinessException(response.getCode(), response.getMessage());
    }
}