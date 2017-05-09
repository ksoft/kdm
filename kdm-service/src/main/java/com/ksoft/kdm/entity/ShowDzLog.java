package com.zcckj.storeshow.entity;

import com.zcckj.storeshow.common.BaseEntity;
import com.zcckj.storeshow.enums.DzChannelEnum;
import com.zcckj.storeshow.enums.ReadFlagEnum;

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
    /** N:未读，Y:已读 */
    private ReadFlagEnum readFlag;
    /** 点赞来源 APP:APP，WX:微信，QT:其它 */
    private DzChannelEnum dzChannel;

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public ReadFlagEnum getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(ReadFlagEnum readFlag) {
        this.readFlag = readFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public DzChannelEnum getDzChannel() {
        return dzChannel;
    }

    public void setDzChannel(DzChannelEnum dzChannel) {
        this.dzChannel = dzChannel;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public Timestamp getCreateTm() {
        return createTm;
    }

    public void setCreateTm(Timestamp createTm) {
        this.createTm = createTm;
    }

    public String getStoreIconUrl() {
        return storeIconUrl;
    }

    public void setStoreIconUrl(String storeIconUrl) {
        this.storeIconUrl = storeIconUrl;
    }
}
