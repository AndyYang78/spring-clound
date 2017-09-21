package com.eryu.core.entity.dto.family;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * 家族列表
 *
 * Created by troubleMan on 2017/7/27.
 */
@Getter
@Setter
public class FamilyDTO {
    /**
     * 家族名称
     */
    @ApiModelProperty(value = "家族名称", required = true)
    private String familyName = "";

    /**
     * 家族头像
     */
    @ApiModelProperty(value = "家族头像", required = true)
    private String familyPortrait = "";


    /**
     * 族长昵称
     */
    @ApiModelProperty(value = "族长昵称", required = true)
    private String familyNick = "";

    /**
     * 族长ID
     */
    @ApiModelProperty(value = "族长ID", required = true)
    private String patriarchId = "";
    /**
     * 家族状态
     */
    @ApiModelProperty(value = "家族状态", required = true)
    private Integer familyStatus;
    /**
     * 分成比率
     */
    @ApiModelProperty(value = "分成比率", required = true)
    private  Double splitRate;

    /**
     * 总贡献值
     */
    @ApiModelProperty(value = "总贡献值", required = true)
    private  Double contributionValue;
    /**
     * 族长收益
     */
    @ApiModelProperty(value = "族长收益", required = true)
    private Double patriarchIncome = 0.0;

    /**
     * 现有成员
     */
    @ApiModelProperty(value = "现有成员", required = true)
    private  Integer members;
    /**
     * 成员上限
     */
    @ApiModelProperty(value = "成员上限", required = true)
    private  Integer maxMembers;

    /**
     * 待审核名称
     */
    @ApiModelProperty(value = "待审核名称", required = true)
    private String examineFamilyName = "";

    /**
     * 待审核家族头像
     */
    @ApiModelProperty(value = "待审核家族头像", required = true)
    private String examineFamilyPortrait = "";

    /**
     * 审核状态
     */
    private Integer examineStatus;
    /**
     * 家族id
     */
    private String familyId;

    /**
     * 家族手机号
     */
    private String familyMoblie;
    /**
     * 家族介绍
     */
    private String introduceFamily;


}
