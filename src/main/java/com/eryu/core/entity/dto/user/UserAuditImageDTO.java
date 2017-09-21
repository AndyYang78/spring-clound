package com.eryu.core.entity.dto.user;

import com.eryu.core.entity.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户实体
 * Created by troubleMam on 2017/06/27.
 */
@Getter
@Setter
public class UserAuditImageDTO extends BaseDTO {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    private String user_name = "";

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态(0:未激活, 1:已激活, 2:冻结)", required = true)
    private Integer status = 1;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", required = true)
    private String mobile = "";

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", required = true)
    private String name = "";
    /**
     * 更换时间
     */
    @ApiModelProperty(value = "更换时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date change_time;
    /**
     * 头像地址/背景地址
     */
    @ApiModelProperty(value = "头像地址", required = true)
    private String avatar = "";

    /**
     * vip等级
     */
    @ApiModelProperty(value = "vip等级", required = true)
    private String vip = "";

    /**
     * id   用户id
     */
    private String id= "";
    /**
     * 图像审核状态
     */
    @ApiModelProperty(value = "状态(0:未审核, 1:通过, 2:不通过)", required = true)
    private Integer imageStatus = 0;
    /**
     * type 类型来源
     */
    private String type;

}
