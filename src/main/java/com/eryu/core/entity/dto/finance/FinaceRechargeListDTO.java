package com.eryu.core.entity.dto.finance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * 财务充值订单明细
 *
 * Created by troubleMan on 2017/8/8.
 */
@Getter
@Setter
public class FinaceRechargeListDTO {
    /**
     * 用户ID
     */
    private String userNo;
    /**
     * 用户昵称
     */
    private String userNick;
    /**
     * 充值金额
     */
    private String rechargeMoney;
    /**
     * 充值时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date rechargeDate;
    /**
     * 交易后钻石
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date payDate;
    /**
     * 支付方式
     */
    private String payType;
    /**
     * 支付状态
     */
    private Integer payState;
    /**
     * 支付凭证
     *(第三方充值订单号)
     */
    private String payNumber;
    /**
     * 充值状态
     */
    private Integer rechargeType;
    /**
     * 充值ID(商户订单号)
     */
    private String rechargeId;
}
