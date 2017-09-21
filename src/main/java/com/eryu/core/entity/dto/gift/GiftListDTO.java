package com.eryu.core.entity.dto.gift;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
/**
 *
 * 返回的礼物列表
 *
 * Created by trouobleMan on 2017/8/1.
 */
@Getter
@Setter
public class GiftListDTO {
    /**
     * 礼物id
     */
    private String giftId;
    /**
     * 查询的名称
     */
    private String giftName;
    /**
     * 礼物图片
     */
    private String giftImg;
    /**
     * 礼物价格
     */
    private Double giftPrice;
    /**
     * 礼物排序
     */
    private Integer giftSort;
    /**
     * 可连击间隔单位秒
     */
    private Integer intervalSecond;
    /**
     * 状态
     */
    private Integer status;
    /**
     *动效名称
     */
    private String dynamicName;
    /**
     * Gif动效预览
     */
    private String gifDynamic;
    /**
     * APNG动效预览
     */
    private String apngDynamic;
    /**
     * 是否可以连击
     */
    private Integer clickTrue;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date create_time;

}
