package com.ksoft.kdm.common.dto;

import java.io.Serializable;

/**
 * MemberInfo
 * 会员信息
 * @author HZH
 * @date 2017/4/25
 */
public class MemberInfo implements Serializable {

    private Long id;

    private String name;

    /** 头像URL */
    //private String iconUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }*/
}
