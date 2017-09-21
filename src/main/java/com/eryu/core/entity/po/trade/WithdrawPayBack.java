package com.eryu.core.entity.po.trade;

import com.eryu.core.entity.po.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 提现返回实体
 * <p>
 * Created by troubleMan on 2017/7/3.
 */
@Entity
@Table(name = "T_WITHDRAW_AFTER_PAY")
@Getter
@Setter
public class WithdrawPayBack extends AbstractEntity {

    @Column(name = "ACCOUNT_NO", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '账号'")
    private String aliNumber;

    @Column(name = "ACCOUNT_TYPE", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '账户类型'")
    private String userMobile ;

    @Column(name = "AMOUNT", columnDefinition = "DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '金额'")
    private Double amount = 0d;

    @Column(name = "CRYSTAL", columnDefinition = "INT(8) NOT NULL DEFAULT 0 COMMENT '水晶数'")
    private Integer crystal ;

    @Column(name = "REALNAME", columnDefinition = "VARCHAR(32) DEFAULT NULL COMMENT '真实姓名'")
    private String userName;

    @Column(name = "STATUS", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '支付状态(0:未支付，1:支付成功，2:支付失败)'")
    private Integer payState = 0;

    @Column(name = "REMARK", columnDefinition = "VARCHAR(32) DEFAULT NULL COMMENT '备注'")
    private String payRemark;

    @Column(name = "APPLY_TIME", columnDefinition = "DATETIME DEFAULT NULL COMMENT '申请时间'")
    private Date applyDate;

    @Column(name = "WITHDRAW_ID", columnDefinition = "VARCHAR(32) DEFAULT NULL COMMENT '提现id'")
    private String withdrawId;

}
