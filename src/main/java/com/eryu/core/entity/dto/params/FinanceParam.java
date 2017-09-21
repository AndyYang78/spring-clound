package com.eryu.core.entity.dto.params;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * 财务的相关的进入的参数
 * Created by troubleMan on 2017/7/27.
 */
@Getter
@Setter
public class FinanceParam {
    private Integer limit;

    private Integer offset;

    /**
     * 打赏用户Id
     */
    private String rewardId;
    /**
     * 打赏用户的昵称
     */
    private String rewardNick;
    /**
     * 受赏用户Id
     */
    private String awardId;
    /**
     * 受赏用户昵称
     */
    private String awardNick;
    /**
     * 钻石数
     */
    private Integer diamonds =0;
    /**
     * 开始时间
     */
    private String dateStart;
    /**
     * 结束时间
     */
    private String dateEnd;
    /**
     * 用户Id(对应后台的耳语用户的账号)
     */
    private String userId;
    /**
     * 用户的昵称
     */
    private String userNick;
    /**
     * 支付凭证
     */
    private String payVoucher;

    /**
     * 充值ID(商户订单号)
     */
    private String rechargeId;
    /**
     * 支付方式
     */
    private String payType;
    /**
     * 充值类型
     */
    private String rechargeType;

    /**
     * 充值金额
     */
    private Integer rechargeMoney =0;

    /**
     * 充值开始时间
     */
    private String rechargeStart;
    /**
     * 充值结束时间
     */
    private String rechargeEnd;
    /**
     * 支付开始时间
     */
    private String payStart;
    /**
     * 支付结束时间
     */
    private String payEnd;
    /**
     * 审核状态
     */
    private Integer examineState;

    /**
     * 充值状态
     */
    private String rechargeState;
    private String[] rechargeStates;

    /**
     * 支付状态
     */
    private String payState;
    private String[] payStates;

}
