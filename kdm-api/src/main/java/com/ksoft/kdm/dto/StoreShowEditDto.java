package com.zcckj.storeshow.dto;


import com.zcckj.storeshow.common.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 编辑卖家秀DTO
 * @author zhangjianbo
 * @date 2017/4/18
 */
@ApiModel(value = "编辑卖家秀对象",description="编辑卖家秀DTO")
public class StoreShowEditDto extends BaseDto {

    private String content;
    /** 图片 */
    @ApiModelProperty(value = "图片地址",required = true)
    private List<String> photoUrlList=new ArrayList<String>();
    /** 轮胎规格ID */
    @ApiModelProperty(value = "轮胎规格ID",required = true)
    private Long tyreSpecId;
    /** 轮胎规格名称 */
    @ApiModelProperty(value = "轮胎规格名称",required = true)
    private String tyreSpecName;
    /** 轮胎品牌ID */
    @ApiModelProperty(value = "轮胎品牌ID",required = true)
    private Long tyreBrandId;
    /** 轮胎品牌名称 */
    @ApiModelProperty(value = "轮胎品牌名称",required = true)
    private String tyreBrandName;
    /** 轮胎宽度 */
    @ApiModelProperty(value = "轮胎宽度",required = true)
    private String tyreKd;
    /** 轮胎扁平率 */
    @ApiModelProperty(value = "轮胎扁平率",required = true)
    private String tyreBpl;
    /** 轮胎内径 */
    @ApiModelProperty(value = "轮胎内径",required = true)
    private String tyreNj;
    /** 车品牌ID */
    @ApiModelProperty(value = "车品牌ID",required = true)
    private Long carBrandId;
    /** 车品牌名称 */
    @ApiModelProperty(value = "车品牌名称",required = true)
    private String carBrandName;
    /** 车系ID */
    @ApiModelProperty(value = "车系ID",required = true)
    private Long carSeriesId;
    /** 车系名称 */
    @ApiModelProperty(value = "车系名称",required = true)
    private String carSeriesName;
    /** 修改人 */
    @ApiModelProperty(value = "修改人",required = true)
    private String modifyBy;

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

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }
}
