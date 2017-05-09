package com.ksoft.kdm.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ksoft.kdm.common.dto.ResponseData;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            out.write(mapper.writeValueAsString(ResponseData.success("登录成功")));
            logger.debug(authentication.getPrincipal());
            out.flush();
            out.close();
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

}
