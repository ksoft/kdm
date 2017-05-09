package com.ksoft.kdm.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageRowBounds;
import com.ksoft.kdm.common.PageDto;
import com.ksoft.kdm.dto.*;
import com.ksoft.kdm.entity.TyreBrandSetting;
import com.ksoft.kdm.mapper.TyreBrandSettingMapper;
import com.ksoft.kdm.service.TyreBrandSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TyreBrandSettingServiceImpl
 *
 * @author HZH
 * @date 2017/4/17
 */
@Service(version = "1.0.0",interfaceClass = TyreBrandSettingService.class)
@Transactional
public class TyreBrandSettingServiceImpl implements TyreBrandSettingService {

    @Autowired
    private TyreBrandSettingMapper tyreBrandSettingMapper;

    @Override
    public ResponseDto<PageDto<TyreBrandSettingListDto>> getPage(TyreBrandSettingSearchDto searchDto) {
        Example e=new Example(TyreBrandSetting.class);
        //排序
        if(StringUtils.isEmpty(searchDto.getSort())){
            e.setOrderByClause("id");
        }else{
            e.setOrderByClause(searchDto.getSort());
        }
        //根据品牌名称模糊查询
        if(StringUtils.isNotEmpty(searchDto.getTyreBrandName())){
            e.createCriteria().andLike("tyreBrandName",searchDto.getTyreBrandName());
        }
        PageRowBounds bounds = new PageRowBounds(searchDto.getOffset(), searchDto.getPageSize());
        List<TyreBrandSetting> list = tyreBrandSettingMapper.selectByExampleAndRowBounds(e,bounds);
        return ResponseDtoFactory.toSuccess("",new PageDto<>(searchDto, toDtos(list), bounds.getTotal().intValue()));
    }

    private List<TyreBrandSettingListDto> toDtos(List<TyreBrandSetting> entitys){
        List<TyreBrandSettingListDto> dtos = new ArrayList<>();
        if(null != entitys && entitys.size() > 0)
            for(TyreBrandSetting entity : entitys){
                if(entity != null){
                    TyreBrandSettingListDto dto = new TyreBrandSettingListDto(entity.getId(), entity.getTyreBrandName(), entity.getPhotoUrl());
                    dtos.add(dto);
                }
            }
        return dtos;
    }

    @Override
    public ResponseDto<Long> save(TyreBrandSettingDto dto) {
        if(null == dto){
            return ResponseDtoFactory.toError("TyreBrandSettingDto不能为空");
        }
        if(null == dto.getTyreBrandId()){
            return ResponseDtoFactory.toError("轮胎品牌ID不能为空");
        }
        if(StringUtils.isBlank(dto.getTyreBrandName())){
            return ResponseDtoFactory.toError("轮胎品牌名称不能为空");
        }
        if(StringUtils.isBlank(dto.getPhotoUrl())){
            return ResponseDtoFactory.toError("图片URL不能为空");
        }
        if(null == dto.getCreateBy()){
            return ResponseDtoFactory.toError("创建人ID不能为空");
        }
        if(exist(null,dto.getTyreBrandId())){
            return ResponseDtoFactory.toError("轮胎品牌已存在不能重复添加");
        }
        TyreBrandSetting entity = dtoToEntity(dto);
        tyreBrandSettingMapper.insertSelective(entity);
        return ResponseDtoFactory.toSuccess(entity.getId());
    }

    private Boolean exist(Long id,Integer tyreBrandId){
        Example e = new Example(TyreBrandSetting.class);
        Example.Criteria c = e.createCriteria();
        c.andEqualTo("tyreBrandId",tyreBrandId);
        if(null != id){
            c.andNotEqualTo("id",id);
        }
        Integer count = tyreBrandSettingMapper.selectCountByExample(e);
        return count>0 ? true : false;
    }

    @Override
    public Boolean delete(Long id) {
        if(null == id || id<=0){
            return false;
        }
        tyreBrandSettingMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public ResponseDto update(TyreBrandSettingDto dto) {
        if(null == dto || null == dto.getId()){
            return ResponseDtoFactory.toError("TyreBrandSettingDto不能为空");
        }
        if(null == dto.getTyreBrandId()){
            return ResponseDtoFactory.toError("轮胎品牌ID不能为空");
        }
        if(StringUtils.isBlank(dto.getTyreBrandName())){
            return ResponseDtoFactory.toError("轮胎品牌名称不能为空");
        }
        if(StringUtils.isBlank(dto.getPhotoUrl())){
            return ResponseDtoFactory.toError("图片URL不能为空");
        }
        if(null == dto.getCreateBy()){
            return ResponseDtoFactory.toError("创建人ID不能为空");
        }
        if(exist(dto.getId(),dto.getTyreBrandId())){
            return ResponseDtoFactory.toError("轮胎品牌已存在不能重复添加");
        }
        TyreBrandSetting entity = dtoToEntity(dto);
        if(null == entity.getModifyTm()){
            entity.setModifyTm(new Timestamp(System.currentTimeMillis()));
        }
        tyreBrandSettingMapper.updateByPrimaryKeySelective(entity);
        return ResponseDtoFactory.toSuccess("");
    }

    private TyreBrandSetting dtoToEntity(TyreBrandSettingDto dto){
        TyreBrandSetting entity = new TyreBrandSetting();
        entity.setId(dto.getId());
        entity.setTyreBrandId(dto.getTyreBrandId());
        entity.setTyreBrandName(dto.getTyreBrandName());
        entity.setPhotoUrl(dto.getPhotoUrl());
        entity.setCreateBy(dto.getCreateBy());
        entity.setCreateTm(dto.getCreateTm());
        entity.setModifyBy(dto.getModifyBy());
        entity.setModifyTm(dto.getModifyTm());
        return entity;
    }
}
