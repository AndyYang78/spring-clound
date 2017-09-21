package com.eryu.core.entity.dto.finance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * 提现列表 详情
 *
 * Created by troubleMan on 2017/8/8.
 */
@Getter
@Setter
public class FinaceWithDrawDTO {

    /**
     * 提现水晶
     */
    private Integer withdrawCrystal;
    /**
     * 提现ID
     */
    private String withdrawId;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户耳语号
     */
    private String userNo;
    /**
     * 昵称
     */
    private String userNick;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 姓名
     */
    private String name;
    /**
     * 身份证号
     */
    private String identity;
    /**
     * 提现金额
     */
    private Double withDrawMoney;
    /**
     * 支付宝帐号
     */
    private String aliNumber;
    /**
     * 申请时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyTime;
    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date examineTime;
    /**
     * 审核状态
     */
    private Integer examineState;
    /**
     * 审核结果
     */
    private String examineResult;
    /**
     * 审核人
     */
    private String examineMan;

}
