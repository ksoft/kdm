package com.ksoft.kdm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by d on 2017/4/7.
 */
@Configuration
@ConfigurationProperties(prefix = "zcckj")
public class AppConfig {
    private String staticPath;


    public String getStaticPath() {
        return staticPath;
    }

    public void setStaticPath(String staticPath) {
        this.staticPath = staticPath;
    }
}
