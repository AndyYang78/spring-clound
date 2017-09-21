package com.eryu.core.entity.po.user;

import com.eryu.core.entity.po.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * API版本号
 * <p>
 * Created by lihui on 2017/7/18.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "T_API_VERSION")
public class APIVersion extends AbstractEntity {

    /**
     * 名称
     */
    @Column(name = "NAME", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '名称'")
    private String name;

    /**
     * 简称
     */
    @Column(name = "SHORT_NAME", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '简称'")
    private String shortName;

    /**
     * API相对地址
     */
    @Column(name = "PATH", columnDefinition = "VARCHAR(128) NOT NULL COMMENT 'API相对地址'")
    private String path;

    /**
     * API版本号
     */
    @Column(name = "API_VERSION", columnDefinition = "INT(8) NOT NULL DEFAULT 1 COMMENT 'API版本号'")
    private Integer apiVersion;
}
