package com.eryu.core.entity.dto.common;

/**
 * 分页数据集封装类
 * <p>
 * Created by marco on 2016/12/23.
 */
public class PagingResultWrapper<T> extends ResultWrapper<T> {

    private Integer totalPage = 1;

    private Integer pageSize = 10;

    /**
     * 总页数
     */
    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * 每页记录数
     */
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
