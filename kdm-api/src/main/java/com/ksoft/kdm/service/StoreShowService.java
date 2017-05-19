package com.ksoft.kdm.service;

import com.ksoft.kdm.dto.ResponseDto;
import com.ksoft.kdm.dto.ShowDzLogDto;
import com.ksoft.kdm.dto.ShowDzLogSaveDto;
import com.ksoft.kdm.dto.ShowDzLogSearchDto;

import java.util.List;
import java.util.Map;

/**
 * StoreShowService
 *
 * @author HZH
 * @date 2017/4/14
 */
public interface StoreShowService {

    /**
     * 卖家秀打标
     * @param id 卖家秀id
     * @param lable  标签
     * @return
     */
    ResponseDto<Boolean> setLable(Long id,String lable);

    /**
     * 卖家秀设定精选/待选
     * @param id 卖家秀id
     * @param jxStatus  精选状态，精选:JX，待选:DX
     * @return
     */
    ResponseDto<Boolean> setJxStatus(Long id,String jxStatus);

    /**
     * 卖家秀设定精选/待选
     * @param id 卖家秀id
     * @param status  状态：启用:Y,禁用:N
     * @return
     */
    ResponseDto<Boolean> setStatus(Long id,String status);

    /**
     * 根据门店Id，获取本门店未读卖家秀数量（首页发现功能）
     * @param storeId 门店id
     * @return 数量
     */
    ResponseDto<Long> getUnReadLogsCnt(Long storeId);

    /**
     * 点赞/取消点赞，成功，返回点赞数
     * @param showDzLogSaveDto
     * @return
     */
    ResponseDto<Integer> dzOpt(ShowDzLogSaveDto showDzLogSaveDto);


    /**
     * 分享，成功，返回分享数
     * @param id 卖家秀id
     * @return
     */
    ResponseDto<Integer> fxOpt(Long id);

    /**
     * 根据门店Id，获取本门店消息列表
     * @param showDzLogSearchDto
     * @return
     */
    ResponseDto<List<ShowDzLogDto>> getDzLogs(ShowDzLogSearchDto showDzLogSearchDto);


    /**
     * 统计累计发布量(total)、累计发布门店(storeTotal)、累计关联轮胎规格(specTotal)
     * @return
     */
    Map<String,Long> statisticStoreAndSpec();
}
