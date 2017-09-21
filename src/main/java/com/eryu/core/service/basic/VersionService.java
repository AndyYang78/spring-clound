package com.eryu.core.service.basic;

import java.util.List;

/**
 * service
 * Created by yangtao on 2017/8/1.
 */
public interface VersionService {

    /**
     * 列表
     */
    List<?> list();

    /**
     * 列表
     */
    List<?> appVersionList();

    /**
     * 列表
     */
    List<?> androidAppVersionList();

    /**
     * 列表
     */
    List<?> getAppSetting();
}
