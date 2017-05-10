package com.ksoft.kdm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangjianbo
 * @date 2017/5/10
 */
@Configuration
public class EurekaConfig {
    @Bean
    //初始化生成bean实例，controller中使用，restTemplate和controller变量名一致
    //类同：private RestTemplate restTemplate =new RestTemplate();
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
