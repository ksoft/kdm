package com.ksoft.kdm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final String AUTHENTICATE_ENDPOINT = "/auth/login";
    private static final String REMEMBER_ME_KEY ="rescue-remember-me";
    private static final String cookieName="KDM-COOKIE";
    private static final String cookieDomain="/";

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 认证提供
     *
     * @return
     */
   /* @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        ReflectionSaltSource s = new ReflectionSaltSource();
        s.setUserPropertyToUse("username");
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new UserLoginPasswordEncoder());
        provider.setUserDetailsService(userDetailsService);
        provider.setSaltSource(s);
        return provider;
    }
*/

    /**
     * 自动登录（记住我）
     * @return
     */
    @Bean
    public RememberMeServices rememberMeServices() {
        TokenBasedRememberMeServices services = new TokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService);
        services.setCookieName(cookieName);//cookie
        services.setTokenValiditySeconds(14 * 60 * 60 * 24);//14 days
//        services.setTokenValiditySeconds(20);//30 days
        services.setParameter("remember");//登陆的参数
        if (!StringUtils.isEmpty(cookieDomain)) {
            services.setCookieDomain(cookieDomain);
        }
        return services;
    }

    /**
     * 验证码过滤器
     * @return
     * @throws Exception
     */
    @Bean
    public UserLoginKaptchaCheckFilter authenticationFilter() throws Exception {
        UserLoginKaptchaCheckFilter authFilter = new UserLoginKaptchaCheckFilter();
        authFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(AUTHENTICATE_ENDPOINT, "POST"));
        authFilter.setAuthenticationManager(super.authenticationManager());
        authFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        authFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        authFilter.setUsernameParameter("username");
        authFilter.setPasswordParameter("password");
        authFilter.setRememberMeServices(rememberMeServices());
        return authFilter;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        web
                .ignoring()
                .antMatchers("/js/**", "/css/**", "/img/**")
                .antMatchers("/api/register")
                .antMatchers("/api/activate")
                .antMatchers("/api/lostpassword")
                .antMatchers("/api/resetpassword")
                .antMatchers("/api/hello");

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

    }

    /**
     * 设置用户密码的加密方式为MD5加密
     * @return
     */
    @Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
    @Bean
    public SessionAuthenticationStrategy sessionAuthenticationStrategy(){
        ConcurrentSessionControlAuthenticationStrategy strategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
        strategy.setExceptionIfMaximumExceeded(true);
        strategy.setMaximumSessions(1);
        return  strategy;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .sessionManagement().sessionAuthenticationStrategy(sessionAuthenticationStrategy()).sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
                .antMatchers("/login",AUTHENTICATE_ENDPOINT).permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/auth/login")
                .usernameParameter("username").passwordParameter("password")
                .failureHandler(authenticationFailureHandler()).successHandler(authenticationSuccessHandler())
                .and()
                .logout().deleteCookies(cookieName).logoutSuccessUrl("/login?logout")
                .and().exceptionHandling().accessDeniedPage("/Access_Denied");

        http.addFilterAt(rememberMeFilter(), RememberMeAuthenticationFilter.class);
        http.addFilterAt(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public UserLoginRememberMeFilter rememberMeFilter() throws Exception {
        return new UserLoginRememberMeFilter(super.authenticationManager(),
                rememberMeServices());
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new UserLoginFailHandler();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new UserLoginSuccessHandler();
    }
}

