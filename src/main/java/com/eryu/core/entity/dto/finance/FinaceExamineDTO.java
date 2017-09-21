package com.eryu.core.entity.dto.finance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * 提现审核列表
 *
 * Created by troubleMan on 2017/8/8.
 */
@Getter
@Setter
@Data
@AllArgsConstructor
public class FinaceExamineDTO {
    /**
     * 支付宝账号
     */
    private String aliNumber;
    /**
     * 用户手机
     */
    private String userMobile;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 金额
     */
    private Double amount;
    /**
     * 水晶
     */
    private Integer crystal;
    /**
     * 申请时间
     */
    private String applyDate;
    /**
     * 打款状态
     */
    private Integer payState;
    /**
     * 打款情况备注
     */
    private String payRemark;

    /**
     * 提现账号id
     */
    private String withdrawId;

}
