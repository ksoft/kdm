package com.ksoft.kdm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("oauth.server")
@Component
public class ServerConfig {
    private String staticUrlPath;
    private String ftpFileBase;

    public String getStaticUrlPath() {
        return staticUrlPath;
    }

    public void setStaticUrlPath(String staticUrlPath) {
        this.staticUrlPath = staticUrlPath;
    }

    public String getFtpFileBase() {
        return ftpFileBase;
    }

    public void setFtpFileBase(String ftpFileBase) {
        this.ftpFileBase = ftpFileBase;
    }
}
