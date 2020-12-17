package com.first.memorandum.dto;

import java.util.List;

public class PageDto<T> {

    private List<T> result ;
    private Long count;
    private Integer pageCurrent;
    private Integer pageSize;

    public PageDto(List<T> result, Long count, Integer pageCurrent, Integer pageSize) {
        this.result = result;
        this.count = count;
        this.pageCurrent = pageCurrent;
        this.pageSize = pageSize;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(Integer pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageDto{" +
                "result=" + result +
                ", count=" + count +
                ", pageCurrent=" + pageCurrent +
                ", pageSize=" + pageSize +
                '}';
    }
}
