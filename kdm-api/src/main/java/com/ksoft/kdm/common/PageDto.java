package com.zcckj.storeshow.common;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * @author HZH
 * @date 2017/4/13
 */
public class PageDto<T> extends BasePageDto {


    /** 总记录数 */
    private Integer totalRecord;
    /** 总页数 */
    private Integer totalPages = 0;
    /** 数据List */
    private List<T> list;


    public PageDto() {
    }

    public PageDto(List<T> list, Integer totalRecord) {
        this.list = list;
        this.totalRecord = totalRecord;
    }

    public PageDto(BasePageDto searchDto, List<T> list, Integer totalRecord) {
        super.setCurrentPage(searchDto.getCurrentPage());
        super.setPageSize(searchDto.getPageSize());
        super.setSort(searchDto.getSort());
        this.list = list;
        this.totalRecord = totalRecord;
    }



    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


    public Integer getTotalPages(){
        if(null != totalRecord && totalRecord>0 && null != super.getPageSize() && super.getPageSize() > 0){
            totalPages = (totalRecord + super.getPageSize() - 1) / super.getPageSize();
        }
        return totalPages;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

}
