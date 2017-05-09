package com.zcckj.storeshow.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zcckj.storeshow.common.BaseDto;
import com.zcckj.storeshow.enums.JxStatusEnum;
import com.zcckj.storeshow.enums.StatusEnum;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * StoreShowDto
 * 卖家秀DTO
 *
 * @author HZH
 * @date 2017/4/13
 */
public class StoreShowDto extends BaseDto {

    private Long storeId;

    private String storeName;

    /** 门店头像URL */
    private String storeIconUrl;

    private String content;

    /** 车品牌名称 */
    private String carBrandName;

    /** 车系名称 */
    private String carSeriesName;

    /** 轮胎品牌名称 */
    private String tyreBrandName;

    /** 轮胎规格名称 */
    private String tyreSpecName;

    /** 点赞数 */
    private Integer dz;

    /** 分享数 */
    private Integer fx;

    @JsonIgnore
    /** 精选状态：DX:待选，JX:精选 */
    private JxStatusEnum jxStatus;

    @JsonIgnore
    /** 启用状态：Y:启用，N:禁用 */
    private StatusEnum status;

    private List<String> photos = new ArrayList<>();

    private Timestamp modifyTm;

    /** 手机1 */
    private String mobilePhone;

    /** 门店地址 */
    private String storeAddress;

    /** 门店经度值 */
    private BigDecimal lng;

    /** 门店纬度值 */
    private BigDecimal lat;

    /** 门头照 */
    private String storePic;

    /** 是否点赞 */
    public Boolean hasUserPlused;

    public Boolean isHasUserPlused() {
        return hasUserPlused;
    }

    public void setHasUserPlused(Boolean hasUserPlused) {
        this.hasUserPlused = hasUserPlused;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDz() {
        return dz;
    }

    public void setDz(Integer dz) {
        this.dz = dz;
    }

    public Integer getFx() {
        return fx;
    }

    public void setFx(Integer fx) {
        this.fx = fx;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public Timestamp getModifyTm() {
        return modifyTm;
    }

    public void setModifyTm(Timestamp modifyTm) {
        this.modifyTm = modifyTm;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public String getStorePic() {
        return storePic;
    }

    public void setStorePic(String storePic) {
        this.storePic = storePic;
    }

    public String getStoreIconUrl() {
        return storeIconUrl;
    }

    public void setStoreIconUrl(String storeIconUrl) {
        this.storeIconUrl = storeIconUrl;
    }

    public String getCarBrandName() {
        return carBrandName;
    }

    public void setCarBrandName(String carBrandName) {
        this.carBrandName = carBrandName;
    }

    public String getCarSeriesName() {
        return carSeriesName;
    }

    public void setCarSeriesName(String carSeriesName) {
        this.carSeriesName = carSeriesName;
    }

    public String getTyreBrandName() {
        return tyreBrandName;
    }

    public void setTyreBrandName(String tyreBrandName) {
        this.tyreBrandName = tyreBrandName;
    }

    public String getTyreSpecName() {
        return tyreSpecName;
    }

    public void setTyreSpecName(String tyreSpecName) {
        this.tyreSpecName = tyreSpecName;
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
}
