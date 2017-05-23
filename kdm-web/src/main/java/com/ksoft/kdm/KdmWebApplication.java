package com.ksoft.kdm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EnableConfigurationProperties
@EnableEurekaClient
@EnableCaching
public class KdmWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(KdmWebApplication.class, args);
	}
}
