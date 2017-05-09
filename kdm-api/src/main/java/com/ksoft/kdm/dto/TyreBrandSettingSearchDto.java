package com.ksoft.kdm.dto;

import com.ksoft.kdm.common.BasePageDto;

/**
 * TyreBrandSettingSearchDto
 * 轮胎品牌设置SearchDto
 * @author HZH
 * @date 2017/4/17
 */
public class TyreBrandSettingSearchDto extends BasePageDto {

    private String tyreBrandName;

    public String getTyreBrandName() {
        return tyreBrandName;
    }

    public void setTyreBrandName(String tyreBrandName) {
        this.tyreBrandName = tyreBrandName;
    }
}
