package com.ksoft.kdm.dto;

import com.ksoft.kdm.common.BaseDto;

import java.sql.Timestamp;

/**
 * @author zhangjianbo
 * @date 2017/4/20
 */
public class ShowDzLogDto extends BaseDto {
    private Long dzLogId;
    private Long storeId;
    private String storeName;
    private String storeIconUrl;
    private String photoUrl;
    private Timestamp createTm;

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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Timestamp getCreateTm() {
        return createTm;
    }

    public void setCreateTm(Timestamp createTm) {
        this.createTm = createTm;
    }

    public String getStoreIconUrl() {
        return storeIconUrl;
    }

    public void setStoreIconUrl(String storeIconUrl) {
        this.storeIconUrl = storeIconUrl;
    }

    public Long getDzLogId() {
        return dzLogId;
    }

    public void setDzLogId(Long dzLogId) {
        this.dzLogId = dzLogId;
    }
}
