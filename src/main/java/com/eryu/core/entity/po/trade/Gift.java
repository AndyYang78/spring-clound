package com.eryu.core.entity.po.trade;

import com.eryu.core.entity.po.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 礼物
 * Created by yangtao on 2017/6/29.
 */
@Getter
@Setter
@Entity
@Table(name = "T_GIFT")
public class Gift extends AbstractEntity {

    /**
     * 礼物名称
     */
    @Column(name = "NAME", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '礼物名称'")
    private String name;

    /**
     * 价值钻石数
     */
    @Column(name = "DIAMOND", columnDefinition = "INT(9) NOT NULL COMMENT '价值钻石数'")
    private Integer diamond;

    @Column(name = "IMG", columnDefinition = "VARCHAR(128) NOT NULL COMMENT '图片'")
    private String img;

    /**
     * 状态(0：不可用, 1：可用)
     */
    @Column(name = "STATUS", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '状态(0：不可用, 1：可用)'")
    private Integer status;

    /**
     * 位置(用于排序)
     */
    @Column(name = "POSITION", columnDefinition = "INT(4) NOT NULL DEFAULT 1 COMMENT '位置(用于排序)'")
    private Integer position;

    /**
     * 是否可连击
     */
    @Column(name = "DOUBLE_HIT", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否可连击'")
    private Boolean doubleHit = false;

    /**
     * 是否全屏
     */
    @Column(name = "FULLSCREEN", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否全屏'")
    private Boolean fullscreen = false;

    /**
     * GIF动画地址
     */
    @Column(name = "ANIMATION_GIFT", columnDefinition = "VARCHAR(128) COMMENT 'GIF动画地址'")
    private String animationGif;

    /**
     * APNG动画地址
     */
    @Column(name = "ANIMATION_APNG", columnDefinition = "VARCHAR(128) COMMENT 'APNG动画地址'")
    private String animationApng;
}
