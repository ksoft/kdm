package com.ksoft.kdm.common.dto;

import java.io.Serializable;

public class PageVO<T> implements Serializable {
    /**
     * 当前页码
     */
    private Integer page = 1;
    /**
     * 页容量
     */
    private Integer pageSize = 20;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 总记录数
     */
    private Long totalElements;
    /**
     * 是否有前一页
     */
    private Boolean hasPrev;
    /**
     * 是否有后一页
     */
    private Boolean hasNext;
    /**
     * 排序方式
     * eg. id_desc ==> id字段降序
     */
    private String sort;
    /**
     * 数据
     */

    private T content;

    public PageVO(T content) {
        this.content = content;
    }

    public PageVO(T content, Integer page, Integer pageSize, Integer totalPage, Long totalElements) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalElements = totalElements;
        this.content = content;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Boolean getHasPrev() {
        return hasPrev;
    }

    public void setHasPrev(Boolean hasPrev) {
        this.hasPrev = hasPrev;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
