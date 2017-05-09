package com.zcckj.storeshow.service;

import com.zcckj.storeshow.common.PageDto;
import com.zcckj.storeshow.dto.ResponseDto;
import com.zcckj.storeshow.dto.TyreBrandSettingDto;
import com.zcckj.storeshow.dto.TyreBrandSettingListDto;
import com.zcckj.storeshow.dto.TyreBrandSettingSearchDto;


/**
 * TyreBrandSettingService
 *  轮胎品牌设置Service
 * @author HZH
 * @date 2017/4/17
 */
public interface TyreBrandSettingService {

    ResponseDto<PageDto<TyreBrandSettingListDto>> getPage(TyreBrandSettingSearchDto searchDto);

    ResponseDto<Long> save(TyreBrandSettingDto dto);

    Boolean delete(Long id);

    ResponseDto update(TyreBrandSettingDto dto);
}
