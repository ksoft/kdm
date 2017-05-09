package com.zcckj.storeshow.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageRowBounds;
import com.zcckj.storeshow.common.PageDto;
import com.zcckj.storeshow.dto.*;
import com.zcckj.storeshow.entity.TyreBrandPatternSetting;
import com.zcckj.storeshow.mapper.TyreBrandPatternSettingMapper;
import com.zcckj.storeshow.service.TyreBrandPatternSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * TyreBrandPatternSettingServiceImpl
 *
 * @author HZH
 * @date 2017/4/17
 */
@Service(version = "1.0.0",interfaceClass = TyreBrandPatternSettingService.class)
@Transactional
public class TyreBrandPatternSettingServiceImpl implements TyreBrandPatternSettingService {

    @Autowired
    private TyreBrandPatternSettingMapper mapper;

    @Override
    public ResponseDto<PageDto<TyreBrandPatternSettingListDto>> getPage(TyreBrandPatternSettingSearchDto searchDto) {
        Example e=new Example(TyreBrandPatternSetting.class);
        Example.Criteria c = e.createCriteria();
        //排序
        if(StringUtils.isEmpty(searchDto.getSort())){
            e.setOrderByClause("id");
        }else{
            e.setOrderByClause(searchDto.getSort());
        }
        //根据品牌查询
        if(null != searchDto.getTyreBrandId()){
            c.andEqualTo("tyreBrandId",searchDto.getTyreBrandId());
        }
        //根据花纹查询
        if(null != searchDto.getTyrePatternId()){
            c.andEqualTo("tyrePatternId",searchDto.getTyrePatternId());
        }
        PageRowBounds bounds = new PageRowBounds(searchDto.getOffset(), searchDto.getPageSize());
        List<TyreBrandPatternSetting> list = mapper.selectByExampleAndRowBounds(e,bounds);
        return ResponseDtoFactory.toSuccess("",new PageDto<>(searchDto,toDtos(list),bounds.getTotal().intValue()));
    }

    private List<TyreBrandPatternSettingListDto> toDtos(List<TyreBrandPatternSetting> entitys){
        List<TyreBrandPatternSettingListDto> dtos = new ArrayList<>();
        if(null != entitys && entitys.size() > 0)
            for(TyreBrandPatternSetting entity : entitys){
                if(entity != null){
                    TyreBrandPatternSettingListDto dto = new TyreBrandPatternSettingListDto(entity.getId(), entity.getTyreBrandName(), entity.getTyrePatternName(), entity.getTyreInsFlag(), entity.getPhotoUrl());
                    dtos.add(dto);
                }
            }
        return dtos;
    }

    @Override
    public ResponseDto<Long> save(TyreBrandPatternSettingDto dto) {
        if(null == dto){
            return ResponseDtoFactory.toError("TyreBrandPatternSettingDto不能为空");
        }
        if(null == dto.getTyreBrandId()){
            return ResponseDtoFactory.toError("轮胎品牌ID不能为空");
        }
        if(StringUtils.isBlank(dto.getTyreBrandName())){
            return ResponseDtoFactory.toError("轮胎品牌名称不能为空");
        }
        if(null == dto.getTyrePatternId()){
            return ResponseDtoFactory.toError("轮胎花纹ID不能为空");
        }
        if(StringUtils.isBlank(dto.getTyrePatternName())){
            return ResponseDtoFactory.toError("轮胎花纹名称不能为空");
        }
        if(StringUtils.isBlank(dto.getPhotoUrl())){
            return ResponseDtoFactory.toError("图片URL不能为空");
        }
        if(null == dto.getCreateBy()){
            return ResponseDtoFactory.toError("创建人ID不能为空");
        }
        if(exist(null,dto.getTyreBrandId(),dto.getTyrePatternId())){
            return ResponseDtoFactory.toError("轮胎品牌和花纹已存在不能重复添加");
        }
        TyreBrandPatternSetting entity = dtoToEntity(dto);
        mapper.insertSelective(entity);
        return ResponseDtoFactory.toSuccess(entity.getId());
    }



    private Boolean exist(Long id,Integer tyreBrandId, Integer tyrePatternId){
        Example e = new Example(TyreBrandPatternSetting.class);
        Example.Criteria c = e.createCriteria();
        c.andEqualTo("tyreBrandId",tyreBrandId).andEqualTo("tyrePatternId",tyrePatternId);
        if(null != id){
            c.andNotEqualTo("id",id);
        }
        Integer count = mapper.selectCountByExample(e);
        return count>0 ? true : false;
    }

    @Override
    public Boolean delete(Long id) {
        if(null == id || id<=0){
            return false;
        }
        mapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public ResponseDto update(TyreBrandPatternSettingDto dto) {
        if(null == dto || null == dto.getId()){
            return ResponseDtoFactory.toError("TyreBrandPatternSettingDto不能为空");
        }
        if(null == dto.getTyreBrandId()){
            return ResponseDtoFactory.toError("轮胎品牌ID不能为空");
        }
        if(StringUtils.isBlank(dto.getTyreBrandName())){
            return ResponseDtoFactory.toError("轮胎品牌名称不能为空");
        }
        if(null == dto.getTyrePatternId()){
            return ResponseDtoFactory.toError("轮胎花纹ID不能为空");
        }
        if(StringUtils.isBlank(dto.getTyrePatternName())){
            return ResponseDtoFactory.toError("轮胎花纹名称不能为空");
        }
        if(StringUtils.isBlank(dto.getPhotoUrl())){
            return ResponseDtoFactory.toError("图片URL不能为空");
        }
        if(null == dto.getCreateBy()){
            return ResponseDtoFactory.toError("创建人ID不能为空");
        }
        if(exist(dto.getId(),dto.getTyreBrandId(),dto.getTyrePatternId())){
            return ResponseDtoFactory.toError("轮胎品牌和花纹已存在不能重复添加");
        }
        TyreBrandPatternSetting entity = dtoToEntity(dto);
        if(null == entity.getModifyTm()){
            entity.setModifyTm(new Timestamp(System.currentTimeMillis()));
        }
        mapper.updateByPrimaryKeySelective(entity);
        return ResponseDtoFactory.toSuccess("");
    }

    @Override
    public ResponseDto<Boolean> queryInsFlag(Long tyre_brand_id, Long tyre_pattern_id) {
        try {
            TyreBrandPatternSetting tyreBrandPatternSetting = null;
            Example e = new Example(TyreBrandPatternSetting.class);
            e.createCriteria()
                    .andCondition("TYRE_BRAND_ID=" + tyre_brand_id)
                    .andCondition("TYRE_PATTERN_ID=" + tyre_pattern_id);
            List<TyreBrandPatternSetting> list = mapper.selectByExample(e);
            if (list != null && list.size() > 0) {
                tyreBrandPatternSetting = list.get(0);
            }
            if (tyreBrandPatternSetting != null) {
                Boolean tyreInsFlag=tyreBrandPatternSetting.getTyreInsFlag();
                if(tyreInsFlag==true) {
                    //通过接口校验是否参加轮胎保，逻辑暂无

                    tyreInsFlag = true;

                    //
                }
                return ResponseDtoFactory.toSuccess(tyreInsFlag);
            } else {
                return ResponseDtoFactory.toError("未找到品牌id="+tyre_brand_id+"，花纹id="+tyre_pattern_id+"的数据");
            }
        }catch (Exception ex){
            return ResponseDtoFactory.toError("查询失败");
        }
    }

    private TyreBrandPatternSetting dtoToEntity(TyreBrandPatternSettingDto dto){
        TyreBrandPatternSetting entity = new TyreBrandPatternSetting();
        entity.setId(dto.getId());
        entity.setTyreBrandId(dto.getTyreBrandId());
        entity.setTyreBrandName(dto.getTyreBrandName());
        entity.setTyrePatternId(dto.getTyrePatternId());
        entity.setTyrePatternName(dto.getTyrePatternName());
        entity.setTyreInsFlag(dto.getTyreInsFlag());
        entity.setPhotoUrl(dto.getPhotoUrl());
        entity.setCreateBy(dto.getCreateBy());
        entity.setCreateTm(dto.getCreateTm());
        entity.setModifyBy(dto.getModifyBy());
        entity.setModifyTm(dto.getModifyTm());
        return entity;
    }
}
