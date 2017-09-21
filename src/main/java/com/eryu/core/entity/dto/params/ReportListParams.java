package com.eryu.core.entity.dto.params;

import lombok.Getter;
import lombok.Setter;

/**
 * 举报信息查询参数
 * Created by yangtao on 2017/7/21.
 */
@Getter
@Setter
public class ReportListParams {
    private String start;
    private String end;
    private String userId;
    private String reportType;
    private Integer state;
}