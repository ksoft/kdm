package com.zcckj.storeshow.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.zcckj.storeshow.common.util.HttpKit;
import com.zcckj.storeshow.service.WechatService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * WechatServiceImpl
 *
 * @author HZH
 * @date 2017/4/27
 */
@Service
public class WechatServiceImpl implements WechatService {

    @Value("${zcckj.wechat.appid}")
    private String appid;
    @Value("${zcckj.wechat.appsecret}")
    private String appsecret;
    private static final String ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code";

    @Override
    public String getOpenid(String code) {
        try {
            String jsonStr = HttpKit.get(ACCESSTOKEN_URL.concat("&appid=") + appid + "&secret=" + appsecret + "&code=" + code);
            Map<String, Object> map = JSONObject.parseObject(jsonStr);
            return map.get("openid").toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public Boolean isWechat(String userAgent) {
        if(StringUtils.isNotEmpty(userAgent) && userAgent.toLowerCase().contains("micromessenger")){
            return true;
        }
        return false;
    }
}
