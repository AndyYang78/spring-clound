package com.eryu.core.entity.po.trade;

import com.eryu.core.entity.po.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 打赏
 * <p>
 * Created by lihui on 2017/7/13.
 */
@Getter
@Setter
@Entity
@Table(name = "T_REWARD")
public class Reward extends AbstractEntity {

    @Column(name = "USER_ID", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '打赏人ID'")
    private String userId;

    @Column(name = "RECEIVER_ID", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '受赏人ID'")
    private String receiverId;

    @Column(name = "BIZ_ID", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '关联业务ID(比如: 房间ID)'")
    private String bizId;

    @Column(name = "FAMILY_ID", columnDefinition = "VARCHAR(32) DEFAULT NULL COMMENT '受赏人家族Id'")
    private String familyId;

    @Column(name = "DIAMOND", columnDefinition = "INT(8) NOT NULL COMMENT '钻石数'")
    private Integer diamond = 0;

    @Column(name = "CRYSTAL", columnDefinition = "INT(8) NOT NULL COMMENT '水晶数'")
    private Integer crystal = 0;

    @Column(name = "APP_VERSION", columnDefinition = "VARCHAR(16) DEFAULT NULL COMMENT 'APP版本号'")
    private String appVersion;

    @Column(name = "PHONE_BRAND", columnDefinition = "VARCHAR(32) DEFAULT NULL COMMENT '手机品牌'")
    private String phoneBrand;

    @Column(name = "PHONE_DEVICE", columnDefinition = "VARCHAR(32) DEFAULT NULL COMMENT '手机设备类型'")
    private String phoneDevice;

    @Column(name = "OS", columnDefinition = "VARCHAR(16) DEFAULT NULL COMMENT '操作系统'")
    private String os;

    @Column(name = "OS_VERSION", columnDefinition = "VARCHAR(16) DEFAULT NULL COMMENT '操作系统版本号'")
    private String osVersion;

    @Column(name = "MARKET_CHANNEL", columnDefinition = "VARCHAR(32) DEFAULT NULL COMMENT '市场渠道'")
    private String marketChannel;

    @Column(name = "GIFT_ID", columnDefinition = "VARCHAR(32) DEFAULT NULL COMMENT '礼物ID'")
    private String giftId;
}
