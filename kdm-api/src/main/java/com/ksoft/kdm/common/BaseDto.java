package com.ksoft.kdm.common;

import java.io.Serializable;

/**
 * @author zhangjianbo
 * @date 2017/4/12
 */
public class BaseDto implements Serializable{

    private Long id;

    public BaseDto(){}

    public BaseDto(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
