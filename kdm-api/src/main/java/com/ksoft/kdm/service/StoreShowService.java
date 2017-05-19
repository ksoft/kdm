package com.ksoft.kdm.service;

import com.ksoft.kdm.dto.ResponseDto;
import com.ksoft.kdm.dto.ShowDzLogSaveDto;

/**
 * StoreShowService
 *
 * @author HZH
 * @date 2017/4/14
 */
public interface StoreShowService {
    /**
     * 分享，成功，返回分享数
     * @param id 卖家秀id
     * @return
     */
    ResponseDto<Integer> fxOpt(Long id);

    /**
     * 点赞/取消点赞，成功，返回点赞数
     * @param showDzLogSaveDto
     * @return
     */
    ResponseDto<Integer> dzOpt(ShowDzLogSaveDto showDzLogSaveDto);

    ResponseDto<Boolean> delete(Long id);
}
