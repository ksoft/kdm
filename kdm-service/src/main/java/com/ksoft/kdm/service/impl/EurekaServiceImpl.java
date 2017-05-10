package com.ksoft.kdm.service.impl;

import com.ksoft.kdm.service.EurekaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhangjianbo
 * @date 2017/5/10
 */
public class EurekaServiceImpl implements EurekaService{

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name){
        System.out.println(name+" welcome . My is microservice provider user");
        return name+" welcome . My is microservice provider user";
    }
}
