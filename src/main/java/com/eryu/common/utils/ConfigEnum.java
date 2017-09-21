package com.eryu.common.utils;

/**
 * 公共的参数配置
 * Created by troubleMan
 * on 2017/7/3.
 */
public enum ConfigEnum {

    PASSWORD("123321PASSWORD");

    private String nCode;

    private ConfigEnum(String nCode) {

        this.nCode = nCode;

    }

    @Override
    public String toString() {

        return String.valueOf(this.nCode);

    }


}
