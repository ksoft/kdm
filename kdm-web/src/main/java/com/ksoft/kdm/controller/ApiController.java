package com.ksoft.kdm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ksoft.kdm.common.dto.MemberInfo;
import com.ksoft.kdm.dto.ResponseDto;
import com.ksoft.kdm.dto.ResponseDtoFactory;
import com.ksoft.kdm.service.MemberService;
import com.ksoft.kdm.service.StoreShowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 移动端接口
 *
 * @author HZH
 * @date 2017/4/14
 */
@Controller
@RequestMapping("/api")
@Api(description = "移动端开放接口", value = "app server api")
public class ApiController {

    @Reference(version = "1.0.0")
    private StoreShowService storeShowService;
    @Autowired
    private MemberService memberService;


    @ApiOperation(value = "分享，成功，返回分享数")
    @ApiImplicitParam(name = "id", value = "卖家秀id", required = true, paramType = "path",dataType = "Long")
    @RequestMapping(value="fxOpt/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Integer> fxOpt(@PathVariable Long id ){
        MemberInfo memberInfo = memberService.getPrincipal();
        if(null == memberInfo){
            ResponseDtoFactory.unAuthorized("登录超时请重新登录");
        }
        return storeShowService.fxOpt(id);
    }
}
