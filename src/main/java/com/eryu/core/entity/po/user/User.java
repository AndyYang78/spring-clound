package com.eryu.core.entity.po.user;

import com.eryu.core.entity.po.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 用户表
 * <p>
 * Created by yangtao on 2017/6/19.
 */
@Entity
@Table(name = "T_USER")
@Setter
@Getter
public class User extends AbstractEntity {

    /**
     * 耳语号
     */
    @NotNull(message = "耳语号不能为空")
    @Column(name = "ERYU_NO", columnDefinition = "VARCHAR(12) NOT NULL COMMENT '耳语号'")
    private String eryuNo;

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    @Column(name = "USERNAME", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '用户名'")
    private String username;

    /**
     * 密码
     */
    @Column(name = "PASSWORD", columnDefinition = "VARCHAR(128) DEFAULT NULL COMMENT '密码'")
    private String password;

    /**
     * 是否设置密码
     */
    @Column(name = "HAS_PASSWORD", columnDefinition = "TINYINT NOT NULL DEFAULT 0 COMMENT '是否设置密码'")
    private Boolean hasPassword = false;

    /**
     * 状态(0:未激活, 1:已激活, 2:冻结)
     */
    @Column(name = "STATUS", columnDefinition = "TINYINT NOT NULL DEFAULT 0 COMMENT '状态(0:未激活, 1:已激活, 2:冻结)'")
    private Short status = 0;

    /**
     * 姓名
     */
    @Column(name = "NAME", columnDefinition = "VARCHAR(16) DEFAULT NULL COMMENT '姓名'")
    private String name;

    /**
     * 性别(0:女，1:男, 2:未知)
     */
    @Column(name = "GENDER", columnDefinition = "TINYINT NOT NULL DEFAULT 2 COMMENT '性别(0:女，1:男, 2:未知)'")
    private Short gender = 0;

    /**
     * 生日
     */
    @Column(name = "BIRTHDAY", columnDefinition = "DATE DEFAULT NULL COMMENT '生日'")
    private Date birthday;

    /**
     * 身份证号
     */
    @Column(name = "ID_CARD", columnDefinition = "VARCHAR(18) DEFAULT NULL COMMENT '身份证号'")
    private String idCard;

    /**
     * 芝麻ID
     */
    @Column(name = "ZHIMA_ID", columnDefinition = "VARCHAR(64) DEFAULT NULL COMMENT '芝麻ID'")
    private String zhimaId;

    /**
     * 是否芝麻认证通过
     */
    @Column(name = "ZHIMA_AUTHED", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否芝麻认证通过'")
    private Boolean zhimaAuthed = false;

    /**
     * 手机号
     */
    @Column(name = "MOBILE", columnDefinition = "VARCHAR(11) DEFAULT NULL COMMENT '手机号'")
    private String mobile;

    /**
     * 头像
     */
    @Column(name = "AVATAR", columnDefinition = "VARCHAR(128) DEFAULT NULL COMMENT '头像地址'")
    private String avatar;

    /**
     * 个人签名
     */
    @Column(name = "SIGNATURE", columnDefinition = "VARCHAR(1024) DEFAULT NULL COMMENT '个人签名'")
    private String signature;

    /**
     * 设备ID
     */
    @Column(name = "UDID", columnDefinition = "VARCHAR(64) DEFAULT NULL COMMENT '设备ID'")
    private String udid;

    /**
     * APP版本号
     */
    @Column(name = "APP_VERSION", columnDefinition = "VARCHAR(16) DEFAULT NULL COMMENT 'APP版本号'")
    private String appVersion;

    /**
     * 手机品牌
     */
    @Column(name = "PHONE_BRAND", columnDefinition = "VARCHAR(32) DEFAULT NULL COMMENT '手机品牌'")
    private String phoneBrand;

    /**
     * 手机设备类型
     */
    @Column(name = "PHONE_DEVICE", columnDefinition = "VARCHAR(32) DEFAULT NULL COMMENT '手机设备类型'")
    private String phoneDevice;

    /**
     * 操作系统
     */
    @Column(name = "OS", columnDefinition = "VARCHAR(16) DEFAULT NULL COMMENT '操作系统'")
    private String os;

    /**
     * 操作系统版本号
     */
    @Column(name = "OS_VERSION", columnDefinition = "VARCHAR(16) DEFAULT NULL COMMENT '操作系统版本号'")
    private String osVersion;

    /**
     * 市场渠道
     */
    @Column(name = "MARKET_CHANNEL", columnDefinition = "VARCHAR(32) DEFAULT NULL COMMENT '市场渠道'")
    private String marketChannel;

    /**
     * 网易云信账户状态(0:未注册，1:正常，2:注册失败, 3:禁用)
     */
    @Column(name = "NETEASE_STATUS", columnDefinition = "TINYINT NOT NULL DEFAULT 0 COMMENT '网易云信账户状态(0:未注册，1:正常，2:注册失败, 3:禁用)'")
    private Short neteaseStatus = 0;

    /**
     * 星座
     */
    @Column(name = "CONSTELLATION", columnDefinition = "VARCHAR(16) DEFAULT NULL COMMENT '星座'")
    private String constellation;

    /**
     * 职业
     */
    @Column(name = "OCCUPATION", columnDefinition = "VARCHAR(32) DEFAULT NULL COMMENT '职业'")
    private String occupation;

    /**
     * 学校
     */
    @Column(name = "SCHOOL", columnDefinition = "VARCHAR(64) DEFAULT NULL COMMENT '学校'")
    private String school;

    /**
     * 主页图片
     */
    @Column(name = "PROFILE_IMG", columnDefinition = "VARCHAR(128) DEFAULT NULL COMMENT '主页图片'")
    private String profileImg;

    /**
     * 省
     */
    @Column(name = "PROVINCE", columnDefinition = "VARCHAR(32) DEFAULT NULL COMMENT '省'")
    private String province;

    /**
     * 城市
     */
    @Column(name = "CITY", columnDefinition = "VARCHAR(32) DEFAULT NULL COMMENT '城市'")
    private String city;

    /**
     * 手机归属地:省
     */
    @Column(name = "MOBILE_PROVINCE", columnDefinition = "VARCHAR(32) DEFAULT NULL COMMENT '手机归属地:省'")
    private String mobileProvince;

    /**
     * 手机归属地:市
     */
    @Column(name = "MOBILE_CITY", columnDefinition = "VARCHAR(32) DEFAULT NULL COMMENT '手机归属地:市'")
    private String mobileCity;

    /**
     * 是否网红
     */
    @Column(name = "FAMOUS", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否网红'")
    private Boolean famous = false;

    /**
     * 冻结时间
     */
    @Column(name = "BLOCK_TIME", columnDefinition = "DATETIME DEFAULT NULL COMMENT '冻结时间'")
    private Date blockTime;

    /**
     * 产品反馈
     */
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JsonIgnore
    private List<Feedback> feedbacks;

}
