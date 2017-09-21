package com.eryu.core.entity.dto.finance;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * 财务账户余额
 *
 * Created by troubleMan on 2017/8/8.
 */
@Getter
@Setter
public class FinaceAccountDTO {
    /**
     * 用户number
     */
    private String userNo;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户昵称
     */
    private String userNick;
    /**
     * 用户状态(1:正常，2：冻结)
     */
    private Integer state;
    /**
     * 充值钻石总额
     */
    private Integer diamondAll;
    /**
     * 账户钻石余额
     */
    private Integer diamondBalance;
    /**
     * 打赏钻石总额
     */
    private Integer diamondReward;
    /**
     * 账户水晶余额
     */
    private Integer crystalBalance;
    /**
     * 收入水晶总额
     */
    private Integer crystalIncome;
    /**
     * 收入水晶总额
     */
    private Integer withdrawalCash;
}
