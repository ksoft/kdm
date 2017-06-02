package com.ksoft.kdm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ksoft.kdm.common.annotation.KdmAuth;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

    @ApiOperation(value = "删除卖家秀")
    @RequestMapping(value="/delete",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> delete(
            @ApiParam(required = true,name="id",value = "卖家秀id")
            @RequestParam(value = "id") Long id){
        return storeShowService.delete(id);
    }

    @RequestMapping(value = "/web/first", method = RequestMethod.GET)
    @ResponseBody
    @KdmAuth(role = "ADMIN333")
    public ResponseDto<Map<String, Object>> firstResp (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("request Url", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        return ResponseDtoFactory.toSuccess(map);
    }

    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto<Map<String, Object>> sessions (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("map"));
        return ResponseDtoFactory.toSuccess(map);
    }
}
