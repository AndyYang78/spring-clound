package com.eryu.core.entity.dto.gift;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * 返回的动效列表
 *
 * Created by trouobleMan on 2017/8/1.
 */
@Getter
@Setter
public class DynamicListDTO {

    /**
     *动效id
     */
    private String dynamicId;
    /**
     * 状态
     */
    private Integer status;
    /**
     *动效名称
     */
    private String dynamicName;
    /**
     * Gif动效预览
     */
    private String gifDynamic;
    /**
     * APNG动效预览
     */
    private String apngDynamic;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date create_time;

}
