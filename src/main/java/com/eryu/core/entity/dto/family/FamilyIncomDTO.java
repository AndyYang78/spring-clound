package com.eryu.core.entity.dto.family;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * 家族收益
 *
 * Created by troubleMan on 2017/7/27.
 */
@Getter
@Setter
public class FamilyIncomDTO {
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
     * 月总贡献值
     */
    @ApiModelProperty(value = "月总贡献值", required = true)
    private Double contributionValue = 0.0;

    /**
     * 月总收益
     */
    @ApiModelProperty(value = "月总收益", required = true)
    private Double contributionIncome = 0.0;

    /**
     * 族长月收益
     */
    @ApiModelProperty(value = "族长月收益", required = true)
    private Double patriarchIncome = 0.0;

}
