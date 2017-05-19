package com.ksoft.kdm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ksoft.kdm.dto.ResponseDto;
import com.ksoft.kdm.dto.ResponseDtoFactory;
import com.ksoft.kdm.service.StoreShowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * index
 *
 * @author HZH
 * @date 2017/3/30
 */
@Api(description = "DUBBO开发测试接口", value = "dubbo test")
@Controller
public class testController {
    @Reference(version = "1.0.0")
    private StoreShowService storeShowService;


    @ApiOperation(value = "统计总发布量、门店量、轮胎规格")
    @RequestMapping(value="statisticStoreAndSpec",method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto<Map<String, Long>> statisticStoreAndSpec(){
        Map<String, Long> map = storeShowService.statisticStoreAndSpec();
        return ResponseDtoFactory.toSuccess(map);
    }
}
