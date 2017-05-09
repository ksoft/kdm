package com.zcckj.storeshow.service;

import com.zcckj.storeshow.dto.StoreShowListDto;
import com.zcckj.storeshow.enums.DzChannelEnum;

import java.util.List;

/**
 * ShowService
 *
 * @author HZH
 * @date 2017/5/4
 */
public interface ShowService {

    /**
     * 初始化点赞信息 最多100条数据
     * @param list
     * @param createBy
     * @param dzChannel
     * @return
     */
    public List<StoreShowListDto> initDzInfo(List<StoreShowListDto> list, String createBy, DzChannelEnum dzChannel);
}
