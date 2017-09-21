package com.eryu.core.entity.dto.params;

import lombok.Data;

/**
 *
 * 礼物请求参数
 * Created by troubleMan on 2017/8/3.
 *
 */
@Data
public class GiftAddRequest {
    /**
     * 礼物id
     */
    private String id;

    /**
     * 版本号
     */
    private Integer version;

    private String name;



    /**
     * 动效名称
     */
    private String animationName;

    /**
     * 价格
     */
    private Integer diamond;


    private String icon;

    /**
     * 图像
     */
    private String img;
    /**
     * 是否可连击
     */
    private Integer doubleHitTime = 0;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 位置
     */
    private Integer position;

    private Boolean doubleHit = false;

    private Boolean fullscreen = false;

    private String animationGif;

    private String animationApng;

    public GiftAddRequest(String id, String name, String animationName, Integer diamond, String icon, String img, Integer doubleHitTime, Integer status, Integer position, Boolean doubleHit, String animationGif, String animationApng) {
        this.id = id;
        this.name = name;
        this.animationName = animationName;
        this.diamond = diamond;
        this.icon = icon;
        this.img = img;
        this.doubleHitTime = doubleHitTime;
        this.status = status;
        this.position = position;
        this.doubleHit = doubleHit;
        this.animationGif = animationGif;
        this.animationApng = animationApng;
    }
}
