package com.eryu.core.entity.dto.request;

import lombok.Data;

/**
 * 修改app的版本号
 * <p>
 * Created by troubleMan on 2017/7/24.
 */
@Data
public class ModifyAPPVersionRequest {

    /**
     * Id
     */
    private String id;

    /**
     * 描述
     */
    private String description;

    //是否强制更新
    private Boolean forceUpdate = false;
    /**
     * 版本号
     */
    private String versionNumber;
    /**
     * versionState
     */
    private String versionState;
    /**
     *描述url
     */
    private String describeptionUrl;
    /**
     * 下载Url
     */
    private String downUrl;

}
