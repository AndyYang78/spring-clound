package com.eryu.core.entity.dto.common;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据集封装类
 * <p>
 * Created by marco on 2016/12/23.
 */
public class ResultWrapper<T> {

    @ApiModelProperty(value = "是否成功", required = true)
    private Boolean success = true;

    @ApiModelProperty(value = "返回总记录数", required = true)
    private Integer total = 0;

    @ApiModelProperty(value = "返回提示信息(一般出错时才会有值)")
    private String message = "";

    @ApiModelProperty(value = "返回数据结果集", required = true)
    private List<T> data;

    public ResultWrapper() {

    }

    public ResultWrapper(T data) {
        if(data!=null){
            this.data = new ArrayList<>(1);
            this.data.add(data);
        }
    }

    public ResultWrapper(List<T> data) {
        this.data = data;
        this.total = data == null ? 0 : data.size();
    }

    /**
     * 是否成功
     *
     * @return
     */
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * 记录数
     */
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 描述
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 返回数据集
     */
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
