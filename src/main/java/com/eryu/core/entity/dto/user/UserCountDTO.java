package com.eryu.core.entity.dto.user;

import com.eryu.core.entity.dto.common.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户数量模型
 * <p>
 * Created by marco on 2017/1/11.
 */
@ApiModel(value = "用户数量")
public class UserCountDTO extends BaseDTO {

    /**
     * 用户数
     */
    @ApiModelProperty(value = "用户数")
    public Integer count = 0;
    /**
     * 付费用户数
     */
    @ApiModelProperty(value = "付费用户数")
    public Integer payCount = 0;

    /**
     * 活跃用户数
     *
     * @return
     */
    private Integer activeUser;

    /**
     * y用户累计注册量
     *
     * @return
     */
    private Integer registerUser;

    /**
     * 时间
     *
     * @return
     */
    private String date;

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(Integer activeUser) {
        this.activeUser = activeUser;
    }

    public Integer getRegisterUser() {
        return registerUser;
    }

    public void setRegisterUser(Integer registerUser) {
        this.registerUser = registerUser;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    public Integer getPayCount() {
        return payCount;
    }

    public void setPayCount(Integer payCount) {
        this.payCount = payCount;
    }
}
