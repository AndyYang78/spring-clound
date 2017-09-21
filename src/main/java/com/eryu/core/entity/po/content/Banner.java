package com.eryu.core.entity.po.content;

import com.eryu.core.entity.po.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BANNER
 * Created by yangtao on 2017/7/5.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "T_BANNER")
public class Banner extends AbstractEntity {

    /**
     * banner位置（可能多个地方都有banner），预留字段
     */
    @Column(name = "POSITION", columnDefinition = "VARCHAR(32) COMMENT '可能多个地方都有banner'")
    private String position;

    /**
     * 标题
     */
    @Column(name = "NAME", columnDefinition = "VARCHAR(64) NOT NULL COMMENT 'BANNER 标题'")
    private String name;

    /**
     * 权重
     */
    @Column(name = "WEIGHT", columnDefinition = "INT(11) COMMENT 'banner位置排序，越小越靠前'")
    private Integer weight = 999;

    /**
     * 图片地址
     */
    @Column(name = "IMG_URL", columnDefinition = "VARCHAR(256) COMMENT '图片地址'")
    private String imgUrl;

    /**
     * 连接地址
     */
    @Column(name = "TARGET_URL", columnDefinition = "VARCHAR(256) COMMENT '图片地址'")
    private String targetUrl;

    /**
     * 是否可用
     */
    @Column(name = "USABLE")
    private boolean usable;

}