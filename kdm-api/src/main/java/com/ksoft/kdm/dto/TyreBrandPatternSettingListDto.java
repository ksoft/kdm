package com.zcckj.storeshow.dto;

import com.zcckj.storeshow.common.BaseDto;

import java.util.Date;

/**
 * TyreBrandPatternSettingDto
 * 轮胎品牌花纹设置ListDto
 * @author HZH
 * @date 2017/4/17
 */
public class TyreBrandPatternSettingListDto extends BaseDto {

    /** 轮胎品牌名称 */
    private String tyreBrandName;
    /** 轮胎花纹名称 */
    private String tyrePatternName;
    /** 是否参加轮胎保： 1:是，0:否 */
    private Boolean tyreInsFlag;
    /** 图片URL */
    private String photoUrl;

    public TyreBrandPatternSettingListDto() {
    }

    public TyreBrandPatternSettingListDto(Long id, String tyreBrandName, String tyrePatternName, Boolean tyreInsFlag, String photoUrl) {
        super(id);
        this.tyreBrandName = tyreBrandName;
        this.tyrePatternName = tyrePatternName;
        this.tyreInsFlag = tyreInsFlag;
        this.photoUrl = photoUrl;
    }

    public String getTyreBrandName() {
        return tyreBrandName;
    }

    public void setTyreBrandName(String tyreBrandName) {
        this.tyreBrandName = tyreBrandName;
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

}
