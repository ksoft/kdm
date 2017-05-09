package com.ksoft.kdm.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ksoft.kdm.dto.StoreShowListDto;
import com.ksoft.kdm.enums.DzChannelEnum;
import com.ksoft.kdm.service.ShowDzLogService;
import com.ksoft.kdm.service.ShowService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ShowServiceImpl
 *
 * @author HZH
 * @date 2017/5/4
 */
@Service
public class ShowServiceImpl implements ShowService{

    @Reference(version = "1.0.0")
    private ShowDzLogService showDzLogService;

    @Override
    public List<StoreShowListDto> initDzInfo(List<StoreShowListDto> list, String createBy, DzChannelEnum dzChannel) {
        if(null != list){
            List<Long> showIdList = new ArrayList<>();
            for(StoreShowListDto dto : list){
                showIdList.add(dto.getId());
            }

            Map<Long,Boolean> dzMap = showDzLogService.existDzByShowIdAndCreateBy(showIdList,createBy,dzChannel);

            for(StoreShowListDto dto : list){
                Boolean dz = false;
                if(null != dzMap){
                    dz = dzMap.get(dto.getId());
                }
                dto.setHasUserPlused(dz);
            }
        }
        return list;
    }
}
