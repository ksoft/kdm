package com.ksoft.kdm.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.ksoft.kdm.common.BaseDto;
import com.ksoft.kdm.enums.JxStatusEnum;
import com.ksoft.kdm.enums.StatusEnum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * StoreShowDto
 * 卖家秀列表DTO
 *
 * @author HZH
 * @date 2017/4/20
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreShowDetailListDto extends BaseDto {

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
    private Date modifyTime;

    private String supplierName;
    /** 消费者点赞数 */
    private Integer cusDzNum;
    /** 精选状态：DX:待选，JX:精选 */
    private String jxStatus;
    /** 标签： BY:保养，MR:美容，LT:轮胎，WX:维修，JY:救援，QT:其它 */
    private String lable;
    /** 启用状态：Y:启用，N:禁用 */
    private String status;

    public StoreShowDetailListDto(){

    }


    public StoreShowDetailListDto(Long id, Long storeId, String storeName, String content, String carName, String gg, Integer dz, Integer fx,
                                  Date modifyTime, Date createTime, String supplierName, Integer cusDzNum, JxStatusEnum jxStatus,String lable,StatusEnum status) {
        super(id);
        this.storeName = (storeName == null ? "" : storeName);
        this.content = (content == null ? "" : content);
        this.carName = (carName == null ? "" : carName);
        this.gg = (gg == null ? "" : gg);
        this.dz = (dz == null ? 0 : dz);
        this.fx = (fx == null ? 0 : fx);
        this.modifyTime = (modifyTime == null ? createTime : modifyTime);
        this.setPhotos(new ArrayList<String>());
        this.supplierName = (supplierName == null ? "" : supplierName);
        this.cusDzNum = (cusDzNum == null ? 0 : cusDzNum);
        this.jxStatus = (jxStatus == null ? "" : jxStatus.getName());
        this.lable = (lable == null ? "" : lable);
        this.status = (status == null ? "" : status.getName());
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getCusDzNum() {
        return cusDzNum;
    }

    public void setCusDzNum(Integer cusDzNum) {
        this.cusDzNum = cusDzNum;
    }

    public String getJxStatus() {
        return jxStatus;
    }

    public void setJxStatus(String jxStatus) {
        this.jxStatus = jxStatus;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
