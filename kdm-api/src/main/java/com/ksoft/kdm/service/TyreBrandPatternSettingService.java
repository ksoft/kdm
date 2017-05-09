package com.ksoft.kdm.service;

import com.ksoft.kdm.common.PageDto;
import com.ksoft.kdm.dto.ResponseDto;
import com.ksoft.kdm.dto.TyreBrandPatternSettingDto;
import com.ksoft.kdm.dto.TyreBrandPatternSettingListDto;
import com.ksoft.kdm.dto.TyreBrandPatternSettingSearchDto;

/**
 * TyreBrandPatternSettingService
 *  轮胎品牌花纹设置Service
 * @author HZH
 * @date 2017/4/17
 */
public interface TyreBrandPatternSettingService {

    ResponseDto<PageDto<TyreBrandPatternSettingListDto>> getPage(TyreBrandPatternSettingSearchDto searchDto);

    ResponseDto<Long> save(TyreBrandPatternSettingDto dto);

    Boolean delete(Long id);

    ResponseDto update(TyreBrandPatternSettingDto dto);

    /**
     * 根据轮胎品牌id和轮胎花纹id，查询是否参加轮胎保
     * @param tyre_brand_id 轮胎品牌id
     * @param tyre_pattern_id 轮胎花纹id
     * @return
     */
    ResponseDto<Boolean> queryInsFlag(Long tyre_brand_id,Long tyre_pattern_id);
}
