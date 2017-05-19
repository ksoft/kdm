package com.ksoft.kdm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ksoft.kdm.dto.ResponseDto;
import com.ksoft.kdm.dto.ResponseDtoFactory;
import com.ksoft.kdm.dto.ShowDzLogSaveDto;
import com.ksoft.kdm.mapper.ShowDzLogMapper;
import com.ksoft.kdm.service.StoreShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * StoreShowServiceImpl
 *
 * @author HZH
 * @date 2017/4/14
 */
@Service(version = "1.0.0",interfaceClass = StoreShowService.class)
@Transactional
public class StoreShowServiceImpl implements StoreShowService {

    @Autowired
    ShowDzLogMapper showDzLogMapper;

    public ResponseDto<Boolean> delete(Long id) {

            return ResponseDtoFactory.toError("操作失败");

    }

    public ResponseDto<Integer> fxOpt(Long id) {

        return ResponseDtoFactory.toError("操作失败");
    }

    @Override
    public ResponseDto<Integer> dzOpt(ShowDzLogSaveDto showDzLogSaveDto) {
        return ResponseDtoFactory.toError("操作失败");
    }
}
