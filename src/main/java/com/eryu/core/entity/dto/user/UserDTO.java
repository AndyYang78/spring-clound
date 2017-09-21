package com.eryu.core.entity.dto.user;

import com.eryu.core.entity.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户实体
 * Created by troubleMam on 2017/06/27.
 */
@Getter
@Setter
public class UserDTO extends BaseDTO {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    private String user_name = "";

    /**
     * 设备ID
     */
    @ApiModelProperty(value = "设备ID", required = true)
    private String udid = "";

    /**
     * 耳语账号
     */
    @ApiModelProperty(value = "耳语账号", required = true)
    private String eryu_no = "";

    /**
     * 用户消费钻石
     */
    @ApiModelProperty(value = "用户消费钻石", required = true)
    private Integer diamond =0;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态(0:未激活, 1:已激活, 2:冻结)", required = true)
    private Integer status = 1;

    /**
     * 账户状态
     */
    @ApiModelProperty(value = "账户状态(0:未激活, 1:已激活, 2:冻结)", required = true)
    private Integer accountStatus = 1;

    /**
     * 个人签名
     */
    @ApiModelProperty(value = "个人签名", required = true)
    private String signa = "";

    /**
     * 手机品牌
     */
    @ApiModelProperty(value = "手机品牌", required = true)
    private String phone_brand = "";

    /**
     * 成长天数
     */
    @ApiModelProperty(value = "成长天数", required = true)
    private Integer grow_day = 0;


    /**
     * 操作系统版本号
     */
    @ApiModelProperty(value = "操作系统版本号", required = true)
    private String os_version = "";

    /**
     * 手机设备类型
     */
    @ApiModelProperty(value = "手机设备类型", required = true)
    private String phone_device = "";

    /**
     * 操作系统
     */
    @ApiModelProperty(value = "操作系统", required = true)
    private String os = "";

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", required = true)
    private String mobile = "";

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", required = true)
    private String name = "";

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号", required = true)
    private String id_card = "";

    /**
     * 是否设置密码
     */
    @ApiModelProperty(value = "是否设置密码", required = true)
    private Integer has_password = 1;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别(0:女，1:男, 2:未知)", required = true)
    private Integer gender = 0;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date create_time;


    /**
     * 冻结时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date frozen_time;


    /**
     * 生日
     */
    @ApiModelProperty(value = "生日", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birthday;

    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址", required = true)
    private String avatar = "";

    /**
     * APP版本号
     */
    @ApiModelProperty(value = "APP版本号", required = true)
    private String app_version = "";

    /**
     * vip等级
     */
    @ApiModelProperty(value = "vip等级", required = true)
    private String vip = "";
    /**
     * 主页图片
     */
    @ApiModelProperty(value = "主页图片", required = true)
    private String profile_img= "";

    /**
     * 学校
     */
    @ApiModelProperty(value = "学校", required = true)
    private String school= "";
    /**
     * 城市
     */
    private String city;

    /**
     * OCCUPATION  职业
     */
    @ApiModelProperty(value = "职业", required = true)
    private String occupation= "";

    /**
     * hobby  兴趣
     */
    @ApiModelProperty(value = "兴趣", required = true)
    private String hobby= "";

    /**
     * id   用户id
     */
    private String id= "";

    /**
     * 身份认证（0未认证，1已认证）
     */
    private Integer isIdentity;
    /**
     * 是否是网红（0不是，1是）
     */
    private Integer isNet=0;
    /**
     * 网易云信账户状态(0:未注册，1:正常，2:注册失败, 3:禁用)
     */
    private Integer isNetease=0;
    /**
     * 充值钻石
     */
    private Integer rechargeDiamonds=0;
    /**
     * 消费钻石
     */
    private Integer tradeDiamonds=0;
    /**
     * 收益水晶
     */
    private Integer incomeDiamonds=0;
    /**
     * 收益金额
     */
    private Double incomeAmount=0.0;
    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginTime;
}
