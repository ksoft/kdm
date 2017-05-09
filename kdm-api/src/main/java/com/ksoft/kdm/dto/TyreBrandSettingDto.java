package com.zcckj.storeshow.dto;

import com.zcckj.storeshow.common.BaseDto;

import java.sql.Timestamp;


/**
 * TyreBrandSettingDto
 * 轮胎品牌设置
 * @author HZH
 * @date 2017/4/17
 */
public class TyreBrandSettingDto extends BaseDto {

    /** 轮胎品牌ID */
    private Integer tyreBrandId;
    /** 轮胎品牌名称 */
    private String tyreBrandName;
    /** 图片URL */
    private String photoUrl;
    /** 创建人 */
    private String createBy;
    /** 创建时间 */
    private Timestamp createTm;
    /** 修改人 */
    private String modifyBy;
    /** 修改时间 */
    private Timestamp modifyTm;

    public Integer getTyreBrandId() {
        return tyreBrandId;
    }

    public void setTyreBrandId(Integer tyreBrandId) {
        this.tyreBrandId = tyreBrandId;
    }

    public String getTyreBrandName() {
        return tyreBrandName;
    }

    public void setTyreBrandName(String tyreBrandName) {
        this.tyreBrandName = tyreBrandName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Timestamp getCreateTm() {
        return createTm;
    }

    public void setCreateTm(Timestamp createTm) {
        this.createTm = createTm;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Timestamp getModifyTm() {
        return modifyTm;
    }

    public void setModifyTm(Timestamp modifyTm) {
        this.modifyTm = modifyTm;
    }
}
