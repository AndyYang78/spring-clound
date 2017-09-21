package com.eryu.core.entity.dto.params;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * 手工充值相关的数据表格
 *
 * Created by troubleMan on 2017/8/21.
 */
@Getter
@Setter
public class FinanceManualParam {

    /**
     * 充值钻石
     */
    private Integer rechargeDiamonds=0;
    /**
     * 扣除钻石
     */
    private Integer deductDiamonds=0;
    /**
     * 充值水晶
     */
    private Integer rechargeCrystal=0;
    /**
     * 扣除水晶
     */
    private Integer deductCrystal=0;
    /**
     * 充值类型
     */
    private String rechargeType;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 用户手机
     */
    private String mobile;
    /**
     * 充值的密码
     */
    private String rechargePassword ;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 用户的id
     */
    private String userId;
}
