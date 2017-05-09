package com.zcckj.storeshow.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.zcckj.storeshow.entity.ShowDzLog;
import com.zcckj.storeshow.enums.DzChannelEnum;
import com.zcckj.storeshow.mapper.ShowDzLogMapper;
import com.zcckj.storeshow.service.ShowDzLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ShowDzLogServiceImpl
 *
 * @author HZH
 * @date 2017/5/4
 */
@Service(version = "1.0.0",interfaceClass = ShowDzLogService.class)
@Transactional
public class ShowDzLogServiceImpl implements ShowDzLogService {

    @Autowired
    private ShowDzLogMapper showDzLogMapper;

    /**
     * 是否点赞 最多查询100条
     * @param createBy
     * @param dzChannel
     * @return
     */
    @Override
    public Map<Long,Boolean> existDzByShowIdAndCreateBy(List<Long> showIds, String createBy, DzChannelEnum dzChannel) {
        Map<Long,Boolean> returnMap = new HashMap<>();
        if (null == showIds || showIds.size()>100){
            return null;
        }
        if(StringUtils.isBlank(createBy)){
            return null;
        }
        Example e=new Example(ShowDzLog.class);
        Example.Criteria criteria = e.createCriteria();
        criteria.andCondition("CREATE_BY='"+createBy+"'");
        criteria.andIn("showId",showIds);
        if(null != dzChannel){
            criteria.andCondition("DZ_CHANNEL='"+dzChannel.toString()+"'");
        }
        List<ShowDzLog> existDzList = showDzLogMapper.selectByExample(e);

        if(null != existDzList){
            for(Long showId : showIds){
                Boolean existDz = false;
                for (int i=0; i<existDzList.size(); i++){
                    ShowDzLog dzLog = existDzList.get(i);
                    if (dzLog.getShowId().equals(showId)){
                        existDz = true;
                        existDzList.remove(i);
                        break;
                    }
                }
                returnMap.put(showId,existDz);
            }
        }
        return returnMap;
    }
}
