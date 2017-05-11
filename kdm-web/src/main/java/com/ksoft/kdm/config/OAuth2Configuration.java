package com.ksoft.kdm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


@Configuration
public class OAuth2Configuration {

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
        private static final String SERVER_RESOURCE_ID = "oauth2server";


        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(SERVER_RESOURCE_ID);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .requestMatchers().antMatchers("/admin")
                    .and()
                    .authorizeRequests()
                    .antMatchers("/admin").access("#oauth2.hasScope('read')");
        }
    }
}
