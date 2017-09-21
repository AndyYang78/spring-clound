package com.eryu.core.service.basic;

import java.util.List;

/**
 * service
 * Created by yangtao on 2017/8/1.
 */
public interface BannerService {

    /**
     * 列表
     */
    List<?> list(String position,Boolean usable);
}
