package com.eryu.common.utils;

import com.eryu.exception.code.Code;

/**
 * Created by lihui on 2017/7/3.
 */
public enum TradeCode implements Code {

    UNKNOW_TYPE(103001, "未知的参数类型"),

    WRONG_FILE(303010, "错误的文件请重新上传新的文件！"),
    PASSWORD_ERROR(303012, "密码错误！"),
    NO_USER(303013, "用户不存在！"),
    NO_FILE(303011, "文件不存在");

    private TradeCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code = 0;

    private String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
