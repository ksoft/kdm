package com.ksoft.kdm.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 保存卖家秀DTO
 * @author zhangjianbo
 * @date 2017/4/17
 */
@ApiModel(value = "保存卖家秀DTO",description="保存卖家秀DTO")
public class StoreShowSaveDto implements Serializable {

    @JsonIgnore
    @ApiModelProperty(value = "门店ID",required = true)
    private Long storeId;
    @JsonIgnore
    @ApiModelProperty(value = "门店名称",required = true)
    private String storeName;
    @JsonIgnore
    @ApiModelProperty(value = "门店头像URL")
    private String storeIconUrl;
    @JsonIgnore
    @ApiModelProperty(value = "经销商id",required = true)
    private Long supplierId;
    @JsonIgnore
    @ApiModelProperty(value = "经销商名称",required = true)
    private String supplierName;
    @ApiModelProperty(value = "文字内容")
    private String content;
    @ApiModelProperty(value = "图片地址",required = true)
    private List<String> photoUrlList=new ArrayList<String>();
    @ApiModelProperty(value = "轮胎SN",required = true)
    private Long tyreSn;
    @JsonIgnore
    @ApiModelProperty(value = "轮胎规格ID")
    private Long tyreSpecId;
    @JsonIgnore
    @ApiModelProperty(value = "轮胎规格名称")
    private String tyreSpecName;
    @JsonIgnore
    @ApiModelProperty(value = "轮胎品牌ID")
    private Long tyreBrandId;
    @JsonIgnore
    @ApiModelProperty(value = "轮胎品牌名称")
    private String tyreBrandName;
    @JsonIgnore
    @ApiModelProperty(value = "轮胎宽度")
    private String tyreKd;
    @JsonIgnore
    @ApiModelProperty(value = "轮胎扁平率")
    private String tyreBpl;
    @JsonIgnore
    @ApiModelProperty(value = "轮胎内径")
    private String tyreNj;
    @ApiModelProperty(value = "车品牌ID",required = true)
    private Long carBrandId;
    @ApiModelProperty(value = "车品牌名称",required = true)
    private String carBrandName;
    @ApiModelProperty(value = "车系ID",required = true)
    private Long carSeriesId;
    @ApiModelProperty(value = "车系名称",required = true)
    private String carSeriesName;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreIconUrl() {
        return storeIconUrl;
    }

    public void setStoreIconUrl(String storeIconUrl) {
        this.storeIconUrl = storeIconUrl;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Long getTyreSpecId() {
        return tyreSpecId;
    }

    public void setTyreSpecId(Long tyreSpecId) {
        this.tyreSpecId = tyreSpecId;
    }

    public String getTyreSpecName() {
        return tyreSpecName;
    }

    public void setTyreSpecName(String tyreSpecName) {
        this.tyreSpecName = tyreSpecName;
    }

    public Long getTyreBrandId() {
        return tyreBrandId;
    }

    public void setTyreBrandId(Long tyreBrandId) {
        this.tyreBrandId = tyreBrandId;
    }

    public String getTyreBrandName() {
        return tyreBrandName;
    }

    public void setTyreBrandName(String tyreBrandName) {
        this.tyreBrandName = tyreBrandName;
    }

    public String getTyreKd() {
        return tyreKd;
    }

    public void setTyreKd(String tyreKd) {
        this.tyreKd = tyreKd;
    }

    public String getTyreBpl() {
        return tyreBpl;
    }

    public void setTyreBpl(String tyreBpl) {
        this.tyreBpl = tyreBpl;
    }

    public String getTyreNj() {
        return tyreNj;
    }

    public void setTyreNj(String tyreNj) {
        this.tyreNj = tyreNj;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getPhotoUrlList() {
        return photoUrlList;
    }

    public void setPhotoUrlList(List<String> photoUrlList) {
        this.photoUrlList = photoUrlList;
    }

    public Long getTyreSn() {
        return tyreSn;
    }

    public void setTyreSn(Long tyreSn) {
        this.tyreSn = tyreSn;
    }

    public Long getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(Long carBrandId) {
        this.carBrandId = carBrandId;
    }

    public String getCarBrandName() {
        return carBrandName;
    }

    public void setCarBrandName(String carBrandName) {
        this.carBrandName = carBrandName;
    }

    public Long getCarSeriesId() {
        return carSeriesId;
    }

    public void setCarSeriesId(Long carSeriesId) {
        this.carSeriesId = carSeriesId;
    }

    public String getCarSeriesName() {
        return carSeriesName;
    }

    public void setCarSeriesName(String carSeriesName) {
        this.carSeriesName = carSeriesName;
    }
}
