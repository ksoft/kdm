package com.ksoft.kdm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ksoft.kdm.dto.ResponseDto;
import com.ksoft.kdm.dto.ResponseDtoFactory;
import com.ksoft.kdm.service.StoreShowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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


    //@ApiIgnore
    @ApiOperation(value = "卖家秀打标")
    @RequestMapping(value="/setLable",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> setLable(
            @ApiParam(required = true,name = "id",value = "卖家秀id")
            @RequestParam(value = "id") Long id,
            @ApiParam(required = true,name = "lable",value = "标签： BY:保养，MR:美容，LT:轮胎，WX:维修，JY:救援，QT:其它，以逗号隔开")
            @RequestParam(value = "lable") String lable){
        return storeShowService.setLable(id,lable);
    }

    //@ApiIgnore
    @ApiOperation(value = "设定精选/待选")
    @RequestMapping(value="/setJxStatus",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> setJxStatus(
            @ApiParam(required = true,name = "id",value = "卖家秀id")
            @RequestParam(value = "id") Long id,
            @ApiParam(required = true,name = "jxStatus",value = "精选状态，精选:JX，待选:DX")
            @RequestParam(value = "jxStatus") String jxStatus){
        return storeShowService.setJxStatus(id,jxStatus);
    }

    //@ApiIgnore
    @ApiOperation(value = "启用/禁用")
    @RequestMapping(value="/setStatus",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> setStatus(
            @ApiParam(required = true,name = "id",value = "卖家秀id")
            @RequestParam(value = "id") Long id,
            @ApiParam(required = true,name = "status",value = "状态，启用:Y，禁用:N")
            @RequestParam(value = "status") String status){
        return storeShowService.setStatus(id,status);
    }
}
