package com.zcckj.storeshow.common.dto;

import com.zcckj.storeshow.enums.DzChannelEnum;

/**
 * CasualInfo
 * 临时用户信息
 * @author HZH
 * @date 2017/5/3
 */
public class CasualInfo {

    /** 临时用户ID */
    private String id;
    /** 来源 */
    private DzChannelEnum source;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DzChannelEnum getSource() {
        return source;
    }

    public void setSource(DzChannelEnum source) {
        this.source = source;
    }
}
