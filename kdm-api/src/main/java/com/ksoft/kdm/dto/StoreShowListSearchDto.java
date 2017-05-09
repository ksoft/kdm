package com.ksoft.kdm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ksoft.kdm.common.BasePageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * StoreShowListSearchDto
 *
 * @author HZH
 * @date 2017/4/13
 */
@ApiModel(value = "StoreShowListSearchDto",description="卖家秀搜索DTO")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StoreShowListSearchDto extends BasePageDto {

    @ApiModelProperty(value = "车品牌IDs")
    private List<Long> carBrandIds;

    @ApiModelProperty(value = "车系IDs")
    private List<Long> carSeriesIds;

    @ApiModelProperty(value = "轮胎品牌IDs")
    private List<Long> tyreBrandIds;

    @ApiModelProperty(value = "轮胎宽度")
    private String tyreKd;

    @ApiModelProperty(value = "轮胎扁平率")
    private String tyreBpl;

    @ApiModelProperty(value = "轮胎内径")
    private String tyreNj;

    @ApiModelProperty(value = "门店ID")
    private Long storeId;

    @ApiModelProperty(value = "仅看自己")
    private Boolean showSelfOnly;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public List<Long> getCarBrandIds() {
        return carBrandIds;
    }

    public void setCarBrandIds(List<Long> carBrandIds) {
        this.carBrandIds = carBrandIds;
    }

    public List<Long> getCarSeriesIds() {
        return carSeriesIds;
    }

    public void setCarSeriesIds(List<Long> carSeriesIds) {
        this.carSeriesIds = carSeriesIds;
    }

    public List<Long> getTyreBrandIds() {
        return tyreBrandIds;
    }

    public void setTyreBrandIds(List<Long> tyreBrandIds) {
        this.tyreBrandIds = tyreBrandIds;
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

    public Boolean getShowSelfOnly() {
        return showSelfOnly;
    }

    public void setShowSelfOnly(Boolean showSelfOnly) {
        this.showSelfOnly = showSelfOnly;
    }
}
