package com.ksoft.kdm.enums;

public enum CusFlagEnum {

	N("否"), Y("是");

    //名称
    public String name;

    CusFlagEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
