package com.ksoft.kdm.config;

import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.alibaba.dubbo.rpc.Exporter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
@ConfigurationProperties(prefix = "dubbo")
@ConditionalOnClass(Exporter.class)
public class ServiceDubboConfig {
    private String zookeeperAddress;
    private String appName;
    private String group;
    private String protocolName;
    private String owner;
    private String serviceGroup;
    private Integer timeOut;

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig config = new ApplicationConfig();
        config.setName(appName);
        config.setOwner(owner);
        List<RegistryConfig> registryConfigs = new ArrayList<RegistryConfig>();
        registryConfigs.add(registryConfig());
        config.setRegistries(registryConfigs);
        return config;
    }


    /**
     * 注入dubbo注册中心配置,基于zookeeper
     *
     * @return
     */
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setGroup(group);
        registryConfig.setAddress(zookeeperAddress);
        registryConfig.setProtocol("zookeeper");
        return registryConfig;
    }

    /**
     * 默认基于dubbo协议提供服务
     *
     * @return
     */
    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(protocolName);
        /**
         * 分布式端口配置。自动配置暴露端口
         */
        protocolConfig.setPort(-1);
        protocolConfig.setSerialization("java");
        return protocolConfig;
    }

    /**
     * dubbo服务提供
     *
     * @return
     */
    @Bean
    public ProviderConfig provider() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(timeOut);
        providerConfig.setProtocol(protocolConfig());
        providerConfig.setApplication(applicationConfig());
        providerConfig.setRegistry(registryConfig());
        return providerConfig;
    }

    /**
     * 设置dubbo扫描包
     *
     * @return
     */
    @Bean
    public static AnnotationBean annotationBean() {
        AnnotationBean bean = new AnnotationBean();
        bean.setPackage("com.ksoft.kdm.service");
        return bean;
    }

    public String getZookeeperAddress() {
        return zookeeperAddress;
    }

    public void setZookeeperAddress(String zookeeperAddress) {
        this.zookeeperAddress = zookeeperAddress;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getServiceGroup() {
        return serviceGroup;
    }

    public void setServiceGroup(String serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }
}
