package com.ksoft.kdm.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ksoft.kdm.dto.ResponseData;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserLoginFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info(exception.getMessage());
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String msg = exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException ?
                    "用户名或密码错误" : exception.getMessage();
            ObjectMapper mapper = new ObjectMapper();
            out.write(mapper.writeValueAsString(ResponseData.fail(msg)));
            out.flush();
            out.close();
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
