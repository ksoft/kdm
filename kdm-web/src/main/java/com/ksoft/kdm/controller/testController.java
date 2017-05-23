package com.ksoft.kdm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ksoft.kdm.dto.ResponseDto;
import com.ksoft.kdm.service.StoreShowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ApiOperation(value = "删除卖家秀")
    @RequestMapping(value="/delete",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> delete(
            @ApiParam(required = true,name="id",value = "卖家秀id")
            @RequestParam(value = "id") Long id){
        return storeShowService.delete(id);
    }


}
