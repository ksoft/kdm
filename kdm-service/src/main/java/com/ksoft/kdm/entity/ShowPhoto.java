package com.zcckj.storeshow.entity;

import com.zcckj.storeshow.common.BaseEntity;

import javax.persistence.Table;

/**
 * ShowPhoto
 * 卖家秀照片
 * @author HZH
 * @date 2017/4/17
 */
@Table(name = "SHOW_PHOTO")
public class ShowPhoto extends BaseEntity {

    private Long showId;
    private String photoUrl;

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
