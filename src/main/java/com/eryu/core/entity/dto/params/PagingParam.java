package com.eryu.core.entity.dto.params;

/**
 * Created by marco on 2017/1/5.
 */
public class PagingParam {

    private Integer pageSize = 10;

    private Integer offset = 0;

    private Integer limit = 0;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
