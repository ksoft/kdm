package com.zcckj.storeshow.service;

/**
 * WechatService
 *
 * @author HZH
 * @date 2017/4/27
 */
public interface WechatService {

    public String getOpenid(String code);

    public Boolean isWechat(String userAgent);
}
