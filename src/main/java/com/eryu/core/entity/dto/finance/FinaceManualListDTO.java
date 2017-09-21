package com.eryu.core.entity.dto.finance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * 财务手工充值订单明细
 *
 * Created by troubleMan on 2017/8/21.
 */
@Getter
@Setter
public class FinaceManualListDTO {
    /**
     * 用户ID
     */
    private String userNo;
    /**
     * 用户昵称
     */
    private String userNick;
    /**
     * 充值钻石
     */
    private Integer rechargeDiamonds;
    /**
     * 扣除钻石
     */
    private Integer deductDiamonds;
    /**
     * 充值水晶
     */
    private Integer rechargeCrystal;
    /**
     * 扣除水晶
     */
    private Integer deductCrystal;
    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date operationDate;
    /**
     * 操作方式
     */
    private String operationType;

    /**
     * 备注
     */
    private String remarks;
}
