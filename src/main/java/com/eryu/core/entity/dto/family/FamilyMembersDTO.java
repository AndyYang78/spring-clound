package com.eryu.core.entity.dto.family;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * 家族成员信息
 * <P></P>
 * Created by troubleMan on 2017/7/28.
 */
@Getter
@Setter
public class FamilyMembersDTO {
    /**
     * 成员昵称
     */
    @ApiModelProperty(value = "成员昵称", required = true)
    private String memberNick = "";
    /**
     * 成员id
     */
    @ApiModelProperty(value = "成员id", required = true)
    private String memberId = "";

    /**
     * 成员身份
     */
    @ApiModelProperty(value = "成员身份", required = true)
    private String memberIdentity = "";

    /**
     * 成员贡献值
     */
    @ApiModelProperty(value = "成员贡献值", required = true)
    private  Double memberContributionValue;

    /**
     * 成员加入时间
     */
    @ApiModelProperty(value = "成员加入时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_time;




}
