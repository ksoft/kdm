package com.zcckj.storeshow.entity;

import com.zcckj.storeshow.common.BaseEntity;
import com.zcckj.storeshow.enums.JxStatusEnum;
import com.zcckj.storeshow.enums.StatusEnum;

import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * StoreShow
 * 卖家秀
 * @author HZH
 * @date 2017/4/14
 */
@Table(name = "STORE_SHOW")
public class StoreShow extends BaseEntity {

    private Long storeId;
    private String storeName;
    /** 门店头像URL */
    private String storeIconUrl;
    private Long supplierId;
    private String supplierName;
    private String content;
    private Long tyreSn;

    public Long getTyreSn() {
        return tyreSn;
    }

    public void setTyreSn(Long tyreSn) {
        this.tyreSn = tyreSn;
    }

    /** 轮胎规格ID */
    private Long tyreSpecId;
    /** 轮胎规格名称 */
    private String tyreSpecName;
    /** 轮胎品牌ID */
    private Long tyreBrandId;
    /** 轮胎品牌名称 */
    private String tyreBrandName;
    /** 轮胎宽度 */
    private String tyreKd;
    /** 轮胎扁平率 */
    private String tyreBpl;
    /** 轮胎内径 */
    private String tyreNj;
    /** 车品牌ID */
    private Long carBrandId;
    /** 车品牌名称 */
    private String carBrandName;
    /** 车系ID */
    private Long carSeriesId;
    /** 车系名称 */
    private String carSeriesName;
    /** 点赞数 */
    private Integer dzNum;
    /** 分享数 */
    private Integer fxNum;
    /** 消费者点赞数 */
    private Integer cusDzNum;
    /** 精选状态：DX:待选，JX:精选 */
    private JxStatusEnum jxStatus;
    /** 启用状态：Y:启用，N:禁用 */
    private StatusEnum status;
    /** 标签： BY:保养，MR:美容，LT:轮胎，WX:维修，JY:救援，QT:其它 */
    private String lable;
    /** 创建时间 */
    private Timestamp createTm;
    /** 修改人 */
    private String modifyBy;
    /** 修改时间 */
    private Timestamp modifyTm;

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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTyreSpecId() {
        return tyreSpecId;
    }

    public void setTyreSpecId(Long tyreSpecId) {
        this.tyreSpecId = tyreSpecId;
    }

    public String getTyreSpecName() {
        return tyreSpecName;
    }

    public void setTyreSpecName(String tyreSpecName) {
        this.tyreSpecName = tyreSpecName;
    }

    public Long getTyreBrandId() {
        return tyreBrandId;
    }

    public void setTyreBrandId(Long tyreBrandId) {
        this.tyreBrandId = tyreBrandId;
    }

    public String getTyreBrandName() {
        return tyreBrandName;
    }

    public void setTyreBrandName(String tyreBrandName) {
        this.tyreBrandName = tyreBrandName;
    }

    public String getTyreKd() {
        return tyreKd;
    }

    public void setTyreKd(String tyreKd) {
        this.tyreKd = tyreKd;
    }

    public String getTyreBpl() {
        return tyreBpl;
    }

    public void setTyreBpl(String tyreBpl) {
        this.tyreBpl = tyreBpl;
    }

    public String getTyreNj() {
        return tyreNj;
    }

    public void setTyreNj(String tyreNj) {
        this.tyreNj = tyreNj;
    }

    public Long getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(Long carBrandId) {
        this.carBrandId = carBrandId;
    }

    public String getCarBrandName() {
        return carBrandName;
    }

    public void setCarBrandName(String carBrandName) {
        this.carBrandName = carBrandName;
    }

    public Long getCarSeriesId() {
        return carSeriesId;
    }

    public void setCarSeriesId(Long carSeriesId) {
        this.carSeriesId = carSeriesId;
    }

    public String getCarSeriesName() {
        return carSeriesName;
    }

    public void setCarSeriesName(String carSeriesName) {
        this.carSeriesName = carSeriesName;
    }

    public Integer getDzNum() {
        return dzNum;
    }

    public void setDzNum(Integer dzNum) {
        this.dzNum = dzNum<0?0:dzNum;
    }

    public Integer getFxNum() {
        return fxNum;
    }

    public void setFxNum(Integer fxNum) {
        this.fxNum = fxNum<0?0:fxNum;
    }

    public Integer getCusDzNum() {
        return cusDzNum;
    }

    public void setCusDzNum(Integer cusDzNum) {
        this.cusDzNum = cusDzNum<0?0:cusDzNum;
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

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Timestamp getCreateTm() {
        return createTm;
    }

    public void setCreateTm(Timestamp createTm) {
        this.createTm = createTm;
    }

    public Timestamp getModifyTm() {
        return modifyTm;
    }

    public void setModifyTm(Timestamp modifyTm) {
        this.modifyTm = modifyTm;
    }

    public String getStoreIconUrl() {
        return storeIconUrl;
    }

    public void setStoreIconUrl(String storeIconUrl) {
        this.storeIconUrl = storeIconUrl;
    }
}
