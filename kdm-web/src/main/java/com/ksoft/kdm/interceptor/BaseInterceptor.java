package com.ksoft.kdm.interceptor;

import com.ksoft.kdm.common.annotation.KdmAuth;
import com.ksoft.kdm.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author zhangjianbo
 * @since 2017-04-10
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseInterceptor.class);
    @Value("${spring.profiles.active}")
    private String profile;
    @Autowired
    private AppConfig config;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.debug("设置页面参数");
        if ("DEV".equalsIgnoreCase(profile)) {
            LOGGER.debug("开发环境，静态资源为本机");
            request.setAttribute("staticPath", request.getContextPath());
        } else {
            LOGGER.debug("非开发环境，静态资源为静态资源服务器");
            request.setAttribute("staticPath", config.getStaticPath());
        }

        //获取方法上的注解
        HandlerMethod hm=(HandlerMethod)handler;
        Method method=hm.getMethod(); if(method.getDeclaringClass().isAnnotationPresent(Controller.class)){
            if(method.isAnnotationPresent(KdmAuth.class))
            {
                System.out.println(method.getAnnotation(KdmAuth.class).role());
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
