package com.zcckj.storeshow.dto;

import com.zcckj.storeshow.common.BasePageDto;

/**
 * TyreBrandPatternSettingSearchDto
 *  轮胎品牌花纹设置SearchDto
 * @author HZH
 * @date 2017/4/17
 */
public class TyreBrandPatternSettingSearchDto  extends BasePageDto {

    /** 轮胎品牌ID */
    private Integer tyreBrandId;
    /** 轮胎花纹ID */
    private String tyrePatternId;

    public Integer getTyreBrandId() {
        return tyreBrandId;
    }

    public void setTyreBrandId(Integer tyreBrandId) {
        this.tyreBrandId = tyreBrandId;
    }

    public String getTyrePatternId() {
        return tyrePatternId;
    }

    public void setTyrePatternId(String tyrePatternId) {
        this.tyrePatternId = tyrePatternId;
    }
}
