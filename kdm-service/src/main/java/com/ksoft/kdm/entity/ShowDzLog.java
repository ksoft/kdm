package com.ksoft.kdm.entity;


import com.ksoft.kdm.common.BaseEntity;

import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * ShowDzLog
 * 点赞记录表
 * @author HZH
 * @date 2017/4/17
 */
@Table(name = "SHOW_DZ_LOG")
public class ShowDzLog extends BaseEntity {

    private Long showId;
    /** 点赞人ID */
    private String createBy;
    /** 点赞人名称 */
    private String createByName;
    /** 门店头像URL */
    private String storeIconUrl;
    /** 点赞时间 */
    private Timestamp createTm;

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getStoreIconUrl() {
        return storeIconUrl;
    }

    public void setStoreIconUrl(String storeIconUrl) {
        this.storeIconUrl = storeIconUrl;
    }

    public Timestamp getCreateTm() {
        return createTm;
    }

    public void setCreateTm(Timestamp createTm) {
        this.createTm = createTm;
    }
}
