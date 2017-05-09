package com.ksoft.kdm.dto;

import com.ksoft.kdm.common.BaseDto;

/**
 * TyreBrandSettingDto
 * 轮胎品牌设置ListDto
 * @author HZH
 * @date 2017/4/17
 */
public class TyreBrandSettingListDto extends BaseDto {

    /** 轮胎品牌名称 */
    private String tyreBrandName;
    /** 图片URL */
    private String photoUrl;

    public TyreBrandSettingListDto() {
    }

    public TyreBrandSettingListDto(Long id, String tyreBrandName, String photoUrl) {
        super(id);
        this.tyreBrandName = tyreBrandName;
        this.photoUrl = photoUrl;
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

}
