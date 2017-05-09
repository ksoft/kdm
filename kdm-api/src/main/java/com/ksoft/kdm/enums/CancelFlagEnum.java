package com.ksoft.kdm.enums;

public enum CancelFlagEnum {

	N("未取消"), Y("已取消");

    //名称
    public String name;

    CancelFlagEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
