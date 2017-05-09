package com.ksoft.kdm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ksoft.kdm.common.PageDto;
import com.ksoft.kdm.common.dto.MemberInfo;
import com.ksoft.kdm.dto.*;
import com.ksoft.kdm.enums.DzChannelEnum;
import com.ksoft.kdm.service.MemberService;
import com.ksoft.kdm.service.ShowService;
import com.ksoft.kdm.service.StoreShowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired
    private ShowService showService;

    @ApiOperation(value = "获取最新的三条卖家秀")
    @RequestMapping(value="showNews",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<List<StoreShowListDto>> showNews(){
        StoreShowListSearchDto searchDto = new StoreShowListSearchDto();
        searchDto.setPageSize(3);
        searchDto.setSort("MODIFY_TM DESC,CREATE_TM DESC");
        ResponseDto<PageDto<StoreShowListDto>> responseDto = storeShowService.getSimplePage(searchDto);
        return ResponseDtoFactory.get(responseDto,responseDto.getData().getList());
    }

    @ApiOperation(value = "卖家秀搜索列表")
    @RequestMapping(value="searchStoreShow",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<PageDto<StoreShowListDto>> searchStoreShow(@RequestBody StoreShowListSearchDto searchDto){
        MemberInfo memberInfo = memberService.getPrincipal();
        if(null == memberInfo){
            ResponseDtoFactory.unAuthorized("登录超时请重新登录");
        }
        if(null != searchDto.getShowSelfOnly() && searchDto.getShowSelfOnly()){
            searchDto.setStoreId(memberInfo.getId());
        }
        searchDto.setSort("MODIFY_TM DESC,CREATE_TM DESC");
        ResponseDto<PageDto<StoreShowListDto>> responseDto = storeShowService.getSimplePage(searchDto);
        //初始化点赞信息 最多100条数据
        showService.initDzInfo(responseDto.getData().getList(),memberInfo.getId().toString(),DzChannelEnum.APP);
        return responseDto;
    }

    @ApiOperation(value = "获取本门店未读消息数量（首页发现功能）")
    @ApiImplicitParam(name = "storeId", value = "门店ID", required = true, paramType = "path",dataType = "Long")
    @RequestMapping(value="getUnReadLogsCnt",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Long> getUnReadLogsCnt(){
        MemberInfo memberInfo = memberService.getPrincipal();
        if(null == memberInfo){
            ResponseDtoFactory.unAuthorized("登录超时请重新登录");
        }
        return storeShowService.getUnReadLogsCnt(memberInfo.getId());
    }

    @ApiOperation(value = "获取本门店被点赞消息列表")
    @RequestMapping(value="getDzLogs",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<List<ShowDzLogDto>> getDzLogs(@RequestBody ShowDzLogSearchDto showDzLogSearchDto){
        MemberInfo memberInfo = memberService.getPrincipal();
        if(null == memberInfo){
            ResponseDtoFactory.unAuthorized("登录超时请重新登录");
        }
        showDzLogSearchDto.setStoreId(memberInfo.getId());
        return storeShowService.getDzLogs(showDzLogSearchDto);
    }

    @ApiOperation(value = "保存卖家秀，返回保存成功时的卖家秀数据id")
    @RequestMapping(value="save",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Long> save(@RequestBody StoreShowSaveDto storeShowSaveDto){
        MemberInfo memberInfo = memberService.getPrincipal();
        if(null == memberInfo){
            ResponseDtoFactory.unAuthorized("登录超时请重新登录");
        }
        storeShowSaveDto.setStoreId(memberInfo.getId());
        storeShowSaveDto.setStoreName(memberInfo.getName());
        //获取经销商信息、获取轮胎信息，逻辑暂无
        storeShowSaveDto.setSupplierId(111L);
        storeShowSaveDto.setSupplierName("");
        storeShowSaveDto.setTyreSpecId(11L);
        storeShowSaveDto.setTyreSpecName("");
        storeShowSaveDto.setTyreBrandId(11L);
        storeShowSaveDto.setTyreBrandName("");
        storeShowSaveDto.setTyreBpl("");
        storeShowSaveDto.setTyreKd("");
        storeShowSaveDto.setTyreNj("");
        //
        return storeShowService.save(storeShowSaveDto);
    }

    @ApiOperation(value = "APP 点赞/取消点赞")
    @RequestMapping(value="dzOpt",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Integer> dzOpt(@RequestBody ShowDzLogSaveDto showDzLogSaveDto){
        MemberInfo memberInfo = memberService.getPrincipal();
        if(null == memberInfo){
            ResponseDtoFactory.unAuthorized("登录超时请重新登录");
        }
        showDzLogSaveDto.setCreateBy(memberInfo.getId().toString());
        showDzLogSaveDto.setCreateByName(memberInfo.getName());
        showDzLogSaveDto.setStoreIconUrl("");//门店头像
        showDzLogSaveDto.setDzChannel(DzChannelEnum.APP);
        return storeShowService.dzOpt(showDzLogSaveDto);
    }

    @ApiOperation(value = "分享，成功，返回分享数")
    @ApiImplicitParam(name = "id", value = "卖家秀id", required = true, paramType = "path",dataType = "Long")
    @RequestMapping(value="fxOpt/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Integer> fxOpt(@PathVariable Long id ){
        return storeShowService.fxOpt(id);
    }

    /*@RequestMapping(value="/save",method = RequestMethod.GET)
    public String save(Map<String,Object> model){

        //userService.saveUser();
        return "index/index";
    }*/

    /*@ApiOperation(value = "获取用户列表信息")
    @PostMapping("/orders")
    public List<UserEntity> getUsers(@RequestBody UserEntity searchVo) {
        UserEntity user = new UserEntity();
        user.setPage(2);
        user.setPageSize(5);
        List<UserEntity> list = userService.getAll2(user);
        return list;
    }*/
}
