package com.ksoft.kdm.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.ksoft.kdm.common.BaseDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * StoreShowDto
 * 卖家秀列表DTO
 *
 * @author HZH
 * @date 2017/4/13
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreShowListDto extends BaseDto {

    private Long storeId;
    private String storeName;
    private String content;
    private String carName;
    /** 轮胎规格 */
    private String gg;
    /** 点赞数 */
    private Integer dz;
    /** 分享数 */
    private Integer fx;
    private List<String> photos;
    private Date modifyTm;
    /** 是否点赞 */
    public Boolean hasUserPlused;

    public StoreShowListDto(){

    }


    public StoreShowListDto(Long id, Long storeId, String storeName, String content, String carName, String gg, Integer dz, Integer fx, Date modifyTime, Date createTime) {
        super(id);
        this.storeId = (storeId == null ? 0l : storeId);
        this.storeName = (storeName == null ? "" : storeName);
        this.content = (content == null ? "" : content);
        this.carName = (carName == null ? "" : carName);
        this.gg = (gg == null ? "" : gg);
        this.dz = (dz == null ? 0 : dz);
        this.fx = (fx == null ? 0 : fx);
        this.modifyTm = (modifyTime == null ? createTime : modifyTime);
        this.setPhotos(new ArrayList<String>());
    }

    public Boolean getHasUserPlused() {
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

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
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

    public Date getModifyTm() {
        return modifyTm;
    }

    public void setModifyTm(Date modifyTm) {
        this.modifyTm = modifyTm;
    }
}
