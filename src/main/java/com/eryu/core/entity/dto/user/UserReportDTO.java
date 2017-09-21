package com.eryu.core.entity.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 *
 * 用户实体
 * Created by troubleMam on 2017/06/27.
 */
@Getter
@Setter
public class UserReportDTO {
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", required = true)
    private String userName = "";

    /**s
     * 昵称
     */
    @ApiModelProperty(value = "昵称", required = true)
    private String nickName = "";

    /**
     * 反馈ID
     */
    @ApiModelProperty(value = "反馈ID", required = true)
    private String udid = "";

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", required = true)
    private String userId = "";

    /**
     * 耳语账号
     */
    @ApiModelProperty(value = "耳语账号", required = true)
    private String eryu_no = "";


    /**
     * 手机设备类型
     */
    @ApiModelProperty(value = "手机设备类型", required = true)
    private String phone_device = "";

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_time;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片", required = true)
    private List<String> picture;

    /**
     * 处理结果
     */
    @ApiModelProperty(value = "处理结果", required = true)
    private Integer dealStatues = 1;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容", required = true)
    private String content = "";

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", required = true)
    private String mobile = "";



}
