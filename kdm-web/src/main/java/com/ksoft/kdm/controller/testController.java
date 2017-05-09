package com.zcckj.storeshow.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zcckj.storeshow.common.PageDto;
import com.zcckj.storeshow.dto.*;
import com.zcckj.storeshow.service.StoreShowService;
import com.zcckj.storeshow.service.TyreBrandPatternSettingService;
import com.zcckj.storeshow.service.TyreBrandSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    private TyreBrandSettingService tyreBrandSettingService;
    @Reference(version = "1.0.0")
    private TyreBrandPatternSettingService tyreBrandPatternSettingService;
    @Reference(version = "1.0.0")
    private StoreShowService storeShowService;


    @ApiOperation(value = "前端查看卖家秀详情")
    @RequestMapping(value="getShowInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<StoreShowDto> getShowInfo(@RequestBody Long id){
        ResponseDto<StoreShowDto> dto = storeShowService.getShowInfo(id);
        return dto;
    }

    @ApiOperation(value = "统计总发布量、门店量、轮胎规格")
    @RequestMapping(value="statisticStoreAndSpec",method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto<Map<String, Long>> statisticStoreAndSpec(){
        Map<String, Long> map = storeShowService.statisticStoreAndSpec();
        return ResponseDtoFactory.toSuccess(map);
    }

    @RequestMapping(value="test",method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto<Object> testPage(){
        TyreBrandPatternSettingSearchDto searchDto = new TyreBrandPatternSettingSearchDto();
        searchDto.setPageSize(10);
        searchDto.setSort("MODIFY_TM DESC");
        ResponseDto<PageDto<TyreBrandPatternSettingListDto>> responseDto = tyreBrandPatternSettingService.getPage(searchDto);
        return ResponseDtoFactory.get(responseDto,responseDto.getData().getList());
    }

    @RequestMapping(value="storeShowDetailPage",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<PageDto<StoreShowDetailListDto>> storeShowDetailPage(@RequestBody StoreShowSearchDto searchDto){
        searchDto.setPageSize(10);
        searchDto.setSort("MODIFY_TM DESC");
        ResponseDto<PageDto<StoreShowDetailListDto>> responseDto = storeShowService.getDetailPage(searchDto);
        return responseDto;
    }

    @RequestMapping(value="testSave",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Long> testSave(@RequestBody TyreBrandSettingDto dto){
        if(null == dto){
            dto = new TyreBrandSettingDto();
            dto.setTyreBrandName("朝阳");
            dto.setTyreBrandId(1);
        }
        return tyreBrandSettingService.save(dto);
    }

    @RequestMapping(value="testUpdate",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto testUpdate(@RequestBody TyreBrandPatternSettingDto dto){
        if(null == dto){
            dto = new TyreBrandPatternSettingDto();
            dto.setTyreBrandName("朝阳");
            dto.setTyreBrandId(1);
        }
        return tyreBrandPatternSettingService.update(dto);
    }

    @RequestMapping(value="testDel/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Object> testDel(@PathVariable Long id){
        if(null == id){
            ResponseDtoFactory.toError("id不能为空");
        }
        Boolean bool = tyreBrandPatternSettingService.delete(id);
        return ResponseDtoFactory.toSuccess(bool.toString());
    }

    //@ApiIgnore
    @ApiOperation(value = "删除卖家秀")
    @RequestMapping(value="/delete",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> delete(
            @ApiParam(required = true,name="id",value = "卖家秀id")
            @RequestParam(value = "id") Long id){
        return storeShowService.delete(id);
    }

    //@ApiIgnore
    @ApiOperation(value = "编辑卖家秀")
    @RequestMapping(value="/update",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> update(StoreShowEditDto storeShowEditDto){
        return storeShowService.update(storeShowEditDto);
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

    //@ApiIgnore
    @ApiOperation(value = "是否参加轮胎保")
    @RequestMapping(value="/queryInsFlag",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> queryInsFlag(
            @ApiParam(required = true,name = "tyre_brand_id",value = "轮胎品牌id")
            @RequestParam(value = "tyre_brand_id") Long tyre_brand_id,
            @ApiParam(required = true,name = "tyre_pattern_id",value = "轮胎花纹id")
            @RequestParam(value = "tyre_pattern_id") Long tyre_pattern_id){
        return tyreBrandPatternSettingService.queryInsFlag(tyre_brand_id,tyre_pattern_id);
    }
}
