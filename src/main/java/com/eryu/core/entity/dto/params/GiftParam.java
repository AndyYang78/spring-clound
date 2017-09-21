package com.eryu.core.entity.dto.params;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * 礼物的入参
 *
 * Created by troubleMan on 2017/8/1.
 */
@Getter
@Setter
public class GiftParam {

    private Integer limit;

    private Integer offset;

    private String id;
    /**
     * 礼物名称
     */
    private String giftName;

    /**
     * 礼物图片
     */
    private String giftImg;
    /**
     * 状态
     */
    private String status;
    /**
     * 是否可连击
     */
    private String clickTrue;

    /**
     * 动效名称
     */
    private String dynamicName;
    /**
     * gif地址
     *
     */
    private String gifName;
    /**
     * apng名称
     */
    private String apngName;
    /**
     * 动效的id
     */
    private String dynamicId;

    /**
     * 礼物价格
     */
    private Integer  diamond;

}
