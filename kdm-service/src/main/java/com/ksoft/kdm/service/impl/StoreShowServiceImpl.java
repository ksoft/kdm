package com.ksoft.kdm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ksoft.kdm.dto.ResponseDto;
import com.ksoft.kdm.dto.ResponseDtoFactory;
import com.ksoft.kdm.dto.ShowDzLogSaveDto;
import com.ksoft.kdm.mapper.ShowDzLogMapper;
import com.ksoft.kdm.service.StoreShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * StoreShowServiceImpl
 *
 * @author HZH
 * @date 2017/4/14
 */
@Service(version = "1.0.0",interfaceClass = StoreShowService.class,group = "kdm")
@Component
@Transactional
public class StoreShowServiceImpl implements StoreShowService {
    @Autowired
    ShowDzLogMapper showDzLogMapper;

    @Override
    public ResponseDto<Integer> fxOpt(Long id) {
        return ResponseDtoFactory.toSuccess(1);
    }

    public ResponseDto<Integer> dzOpt(ShowDzLogSaveDto showDzLogSaveDto) {

        return ResponseDtoFactory.toSuccess(1);
    }
    @Override
    public Map<String, Long> statisticStoreAndSpec() {
        return null;
    }
}
