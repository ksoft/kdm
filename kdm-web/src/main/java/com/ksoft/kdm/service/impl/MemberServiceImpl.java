package com.zcckj.storeshow.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.zcckj.storeshow.common.constants.Constants;
import com.zcckj.storeshow.common.dto.CasualInfo;
import com.zcckj.storeshow.common.dto.MemberInfo;
import com.zcckj.storeshow.common.util.HttpKit;
import com.zcckj.storeshow.enums.DzChannelEnum;
import com.zcckj.storeshow.service.MemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * MemberServiceImpl
 * 会员信息
 * @author HZH
 * @date 2017/4/25
 */
@Service
public class MemberServiceImpl implements MemberService {

    private static Log log = LogFactory.getLog(MemberServiceImpl.class);

    @Autowired
    private HttpServletRequest request;

    public boolean validateTicket(HttpServletRequest request) {
        try {
            String ticket = getTicket(request);

            if (StringUtils.isEmpty(ticket)) {
                if (log.isDebugEnabled()) {
                    log.debug("ticket,为空，返回false");
                }
                return false;
            }
            /*if (AuthUtils.validateTicket(ticket, supPassportUrl)) {
                if (log.isDebugEnabled()) {
                    log.debug("去认证系统校验ticket成功，返回true ticket===>>" + ticket);
                }
                return true;
            }*/
            if (log.isDebugEnabled()) {
                log.debug("拦截器获取到ticket为：" + ticket + ",去单点系统校验ticket失败");
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public MemberInfo getPrincipal() {
        String ticket = getTicket(request);
        if(StringUtils.isNotEmpty(ticket)){

        }
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setId(1l);
        memberInfo.setName("车空间波波旗舰店");
        return memberInfo;
    }

    private String getTicket(HttpServletRequest httpRequest) {
        String ticket = httpRequest.getHeader(Constants.PRINCIPAL_ATTRIBUTE_NAME);
        //ticket = StringUtils.isEmpty(ticket) ? WebUtils.getCookie(httpRequest, Constants.PRINCIPAL_ATTRIBUTE_NAME) : ticket;
        ticket = StringUtils.isEmpty(ticket) ? httpRequest.getHeader("key") : ticket;
        return ticket;
    }

    /**
     * 获取临时用户信息
     * @param request
     * @return
     */
    public CasualInfo getCasualInfo(HttpServletRequest request){
        CasualInfo casualInfo = new CasualInfo();
        String openid = (String) request.getSession().getAttribute(Constants.WECHAT_OPENID_ATTRIBUTE_NAME);
        if(StringUtils.isNotEmpty(openid)){//微信
            casualInfo.setId(openid);
            casualInfo.setSource(DzChannelEnum.WX);
        }else{
            Cookie[] cookies = request.getCookies();
            Cookie dzcookie=null;
            if(null != cookies && cookies.length>0) {
                for (Cookie c : cookies) {
                    if (Constants.DZ_COOKIE.equals(c.getName())) {
                        dzcookie = c;
                        break;
                    }
                }
            }
            if(dzcookie!=null){
                casualInfo.setId(dzcookie.getValue());
            }else{
                casualInfo.setId(HttpKit.getRemortIP(request));
            }
            casualInfo.setSource(DzChannelEnum.QT);
        }
        return casualInfo;
    }
}
