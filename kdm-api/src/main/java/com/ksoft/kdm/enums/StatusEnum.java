package com.zcckj.storeshow.enums;

public enum StatusEnum {

	Y("启用"), N("禁用");

    //名称
    public String name;

    StatusEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
