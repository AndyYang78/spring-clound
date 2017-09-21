package com.eryu.core.entity.dto.finance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * 财务消费明细
 *
 * Created by troubleMan on 2017/8/8.
 */
@Getter
@Setter
public class FinaceAccountDetailDTO {
    /**
     * 收入/支出 0 支出 1收入
     */
    private Integer type;
    /**
     * 交易类型
     */
    private String tradeType;
    /**
     * 交易钻石
     */
    private Integer tradeDiamond =0;
    /**
     * 交易前钻石
     */
    private Integer tradeBeforeDiamond=0;
    /**
     * 交易后钻石
     */
    private Integer tradeAfterDiamond=0;
    /**
     * 交易水晶
     */
    private Integer tradeCrystal=0;
    /**
     * 交易前水晶
     */
    private Integer tradeBeforeCrystal=0;
    /**
     * 交易后水晶
     */
    private Integer tradeAfterCrystal=0;
    /**
     * 提现金额
     */
    private Double withdrawalCash=0.0;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
