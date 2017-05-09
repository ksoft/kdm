package com.ksoft.kdm.service;

import com.ksoft.kdm.common.PageDto;
import com.ksoft.kdm.dto.ResponseDto;
import com.ksoft.kdm.dto.TyreBrandSettingDto;
import com.ksoft.kdm.dto.TyreBrandSettingListDto;
import com.ksoft.kdm.dto.TyreBrandSettingSearchDto;


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
