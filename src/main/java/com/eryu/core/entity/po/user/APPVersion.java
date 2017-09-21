package com.eryu.core.entity.po.user;

import com.eryu.core.entity.po.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * APP版本
 * <p>
 * Created by yangtao on 2017/8/11.
 */
@Entity
@Table(name = "T_APP_VERSION")
@Setter
@Getter
public class APPVersion extends AbstractEntity {

    //版本名称（版本号）
    /**
     * 版本名称（版本号）
     */
    @Column(name = "NAME", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '版本名称（版本号）'")
    private String name;

    //版本号
    /**
     * 版本号
     */
    @Column(name = "VERSION_NUMBER", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '版本号'")
    private String versionNumber;

//    /**
//     * 自增长版本号
//     */
//    @Column(name = "AUTO_INDEX", unique = true, columnDefinition = "INT(8) NOT NULL COMMENT '配置键'")
//    //（内部使用）自动迭代的版本号
//    private Integer autoIndex = 0;

    /**
     * 操作系统
     */
    @Column(name = "OS", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '操作系统'")
    private String os;

    /**
     * 版本状态
     */
    @Column(name = "VERSION_STATE", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '版本状态'")
    private String versionState;

    //更新描述
    /**
     * 描述
     */
    @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR(2048) NOT NULL COMMENT '配置值'")
    private String description;

    //是否强制更新
    /**
     * 是否强制更新
     */
    @Column(name = "FORCE_UPDATE", columnDefinition = "INT(2) NOT NULL COMMENT '配置值'")
    private Boolean forceUpdate = false;
}
