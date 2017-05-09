package com.zcckj.storeshow.entity;

import com.zcckj.storeshow.common.BaseEntity;

import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * TyreBrandPatternSettingDto
 * 轮胎品牌花纹设置表
 * @author HZH
 * @date 2017/4/17
 */
@Table(name = "TYRE_BRAND_PATTERN_SETTING")
public class TyreBrandPatternSetting extends BaseEntity {

    /** 轮胎品牌ID */
    private Integer tyreBrandId;
    /** 轮胎品牌名称 */
    private String tyreBrandName;
    /** 轮胎花纹ID */
    private Integer tyrePatternId;
    /** 轮胎花纹名称 */
    private String tyrePatternName;
    /** 是否参加轮胎保： 1:是，0:否 */
    private Boolean tyreInsFlag;
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

    public Integer getTyrePatternId() {
        return tyrePatternId;
    }

    public void setTyrePatternId(Integer tyrePatternId) {
        this.tyrePatternId = tyrePatternId;
    }

    public String getTyrePatternName() {
        return tyrePatternName;
    }

    public void setTyrePatternName(String tyrePatternName) {
        this.tyrePatternName = tyrePatternName;
    }

    public Boolean getTyreInsFlag() {
        return tyreInsFlag;
    }

    public void setTyreInsFlag(Boolean tyreInsFlag) {
        this.tyreInsFlag = tyreInsFlag;
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
