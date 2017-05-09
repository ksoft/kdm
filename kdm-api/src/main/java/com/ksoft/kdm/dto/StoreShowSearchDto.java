package com.zcckj.storeshow.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zcckj.storeshow.enums.JxStatusEnum;
import com.zcckj.storeshow.enums.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Date;


/**
 * StoreShowListSearchDto
 *
 * @author HZH
 * @date 2017/4/13
 */
@ApiModel(value = "StoreShowSearchDto",description="卖家秀搜索DTO")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StoreShowSearchDto extends StoreShowListSearchDto {

    @ApiModelProperty(value = "门店名称")
    private String storeName;

    @ApiModelProperty(value = "经销商名称")
    private String supplierName;

    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date beginDate;

    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    @ApiModelProperty(value = "精选状态：DX:待选，JX:精选")
    private JxStatusEnum jxStatus;

    @ApiModelProperty(value = "启用状态：Y:启用，N:禁用")
    private StatusEnum status;

    @ApiModelProperty(value = "标签： BY:保养，MR:美容，LT:轮胎，WX:维修，JY:救援，QT:其它")
    private String lable;


    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public JxStatusEnum getJxStatus() {
        return jxStatus;
    }

    public void setJxStatus(JxStatusEnum jxStatus) {
        this.jxStatus = jxStatus;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

}

