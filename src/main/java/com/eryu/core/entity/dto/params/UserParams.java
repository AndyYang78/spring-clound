package com.eryu.core.entity.dto.params;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户的入参
 * Created by marco on 2016/12/30.
 */
@Getter
@Setter
public class UserParams extends PagingParam {

    private Integer limit;

    private Integer offset;
    /**
     * 耳语的no
     */
    private String erNo;
    private String mobile;
    /**
     * 昵称
     */
    private String nickname;
    private Integer gender;
    private Integer type;
    /**
     * 网易云信账户状态
     */
    private Integer isNetease;
    /**
     * 用户的状态
     */
    private Integer status;

    private String dateStart;

    private String dateEnd;


    private String sort;
    /**
     * vip用户
     */
    private String vip;
    /**
     * 分别记录各种VIP等级
     */
    private String[] vips;
    /**
     * 手机类型
     */
    private String device;
    /**
     * 是否认证
     */
    private String is_auth;
    /**
     * 姓名
     */
    private String name;
    /**
     * 处理状态
     */
    private Integer dealStatus;
    /**
     * 是否是网红(0不是，1是)
     */
    private Integer isNet;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 来源
     */
    private String sourceStatus;

}
