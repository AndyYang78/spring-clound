package com.eryu.core.entity.po.user;

import com.eryu.core.entity.po.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Android APP 版本(用于自动更新)
 * Created by yangtao on 2017/8/11.
 */
@Entity
@Table(name = "T_APP_VERSION_ANDROID")
@Getter
@Setter
public class AndroidAppVersion extends AbstractEntity {

    /**
     * 版本名称
     */
    @Column(name = "NAME", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '版本名称（版本号）'")
    private String name;

    /**
     * 版本号
     */
    @Column(name = "VERSION_CODE", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '版本号（android apk）'")
    private String versionCode;

    /**
     * 自增长版本号
     */
    @Column(name = "AUTO_INDEX", unique = true, columnDefinition = "INT(8) NOT NULL COMMENT '配置键'")
    private Integer autoIndex = 0;

    /**
     * 下载地址
     */
    @Column(name = "DOWNLOAD_URL", columnDefinition = "VARCHAR(256) COMMENT '下载地址'")
    private String downloadUrl;

    /**
     * 描述
     */
    @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR(2048) COMMENT '描述'")
    private String description;

    /**
     * 详细描述地址
     */
    @Column(name = "DESCRIPTION_URL", columnDefinition = "VARCHAR(256) COMMENT '详细描述地址'")
    private String descriptionUrl;

    /**
     * 是否强制更新
     */
    @Column(name = "FORCE_UPDATE", columnDefinition = "INT(2) NOT NULL COMMENT '是否强制更新'")
    private Boolean forceUpdate = false;
}
