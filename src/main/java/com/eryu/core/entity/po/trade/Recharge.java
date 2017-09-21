package com.eryu.core.entity.po.trade;

import com.eryu.core.entity.po.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 充值实体
 * <p>
 * Created by lihui on 2017/7/3.
 */
@Entity
@Table(name = "T_RECHARGE")
@Getter
@Setter
public class Recharge extends AbstractEntity {

    @Column(name = "USER_ID", updatable = false, columnDefinition = "VARCHAR(32) NOT NULL COMMENT '用户ID'")
    private String userId;

    @Column(name = "AMOUNT", columnDefinition = "DECIMAL(11, 2) NOT NULL DEFAULT 0 COMMENT '充值金额'")
    private Double amount = 0d;

    @Column(name = "DISCOUNT_AMOUNT", columnDefinition = "DECIMAL(11, 2) NOT NULL DEFAULT 0 COMMENT '优惠金额'")
    private Double discountAmount = 0d;

    @Column(name = "PAY_AMOUNT", columnDefinition = "DECIMAL(11, 2) NOT NULL DEFAULT 0 COMMENT '支付金额'")
    private Double payAmount = 0d;

    @Column(name = "DIAMOND", columnDefinition = "INT(11) NOT NULL DEFAULT 0 COMMENT '充值钻石数'")
    private Integer diamond = 0;

    @Column(name = "PAY_TYPE", columnDefinition = "VARCHAR(16) DEFAULT NULL COMMENT '付款类型'")
    private String payType;

    @Column(name = "PAY_STATUS", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '支付状态(0:未支付，1:支付成功，2:支付失败)'")
    private Integer payStatus = 0;

    @Column(name = "PAY_TRADE_NO", columnDefinition = "VARCHAR(32) DEFAULT NULL COMMENT '支付交易号'")
    private String payTradeNo;

    @Column(name = "PAY_TIME", columnDefinition = "DATETIME DEFAULT NULL COMMENT '支付时间'")
    private Date payTime;
}
