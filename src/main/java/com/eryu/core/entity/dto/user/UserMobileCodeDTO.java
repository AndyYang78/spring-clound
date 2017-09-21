package com.eryu.core.entity.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * 用户手机号码列表
 * Created by troubleMam on 2017/06/27.
 */
@Getter
@Setter
public class UserMobileCodeDTO {

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", required = true)
    private String mobile = "";

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码", required = true)
    private String code = "";

    /**
     * 发送时间
     */
    @ApiModelProperty(value = "发送时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_time;

    /**
     * 发送状态
     */
    @ApiModelProperty(value = "发送状态", required = true)
    private Integer code_status = 1;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", required = true)
    private String codeName = "";

}
