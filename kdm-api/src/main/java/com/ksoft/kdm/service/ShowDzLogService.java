package com.zcckj.storeshow.service;

import com.zcckj.storeshow.enums.DzChannelEnum;

import java.util.List;
import java.util.Map;

/**
 * ShowDzLogService
 *
 * @author HZH
 * @date 2017/5/4
 */
public interface ShowDzLogService {

    /**
     * 是否点赞  最多查询100条
     * @param createBy
     * @param dzChannel
     * @return
     */
    public Map<Long,Boolean> existDzByShowIdAndCreateBy(List<Long> showIds, String createBy, DzChannelEnum dzChannel);
}
