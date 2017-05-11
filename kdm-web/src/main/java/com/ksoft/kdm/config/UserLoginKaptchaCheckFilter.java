package com.ksoft.kdm.config;

import com.ksoft.kdm.common.constants.Constants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码过滤器
 */
public class UserLoginKaptchaCheckFilter extends UsernamePasswordAuthenticationFilter {

/*    @Reference(version = "1.0.0")
    private CustomerServiceService customerServiceService;*/
    private static final  String USERNAME = "username";
    private static final  String PASSWORD = "password";
    private static final  String CONTENT_TYPE = "Content-Type";
    @Override
    protected String obtainPassword(HttpServletRequest request) {
        if (request.getHeader(CONTENT_TYPE).contains("application/json")) {
            return (String) request.getAttribute(PASSWORD);
        } else {
            return super.obtainPassword(request);
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            /*Object p = authentication.getPrincipal();
            if(p instanceof AuthUser){
                AuthUser user = (AuthUser) p;
                if(log.isDebugEnabled()){
                    log.debug("----> 验证用户状态");
                }
                CustomerServiceDto user1 =  customerServiceService.getUserByUsername(user.getUsername(), false);
                if(user1.getStatus()== Status.DISABLED){
                    authentication.setAuthenticated(false);
                }
            }*/
        }
        super.doFilter(req, res, chain);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        if (request.getHeader(CONTENT_TYPE).contains("application/json")) {
            return (String) request.getAttribute(USERNAME);
        } else {
            return super.obtainUsername(request);
        }
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String genCode = this.obtainGeneratedCaptcha(request);
        String inputCode = this.obtainCaptcha(request);
        if(genCode == null||!genCode.equalsIgnoreCase(inputCode))
            throw new AuthenticationServiceException("验证码错误");
        return super.attemptAuthentication(request, response);
    }

    /**
     * 登录请求中验证码
     * @param request
     * @return
     */
    protected String obtainCaptcha(HttpServletRequest request){
        return request.getParameter("code");
    }

    /**
     * 从session获取验证码
     * @param request
     * @return
     */
    protected String obtainGeneratedCaptcha (HttpServletRequest request){
        return (String)request.getSession().getAttribute(Constants.SESSION_KEY_KAPTCHA);
    }

}
