package com.zcckj.storeshow.enums;

public enum JxStatusEnum {

	JX("精选"), DX("待选");

    //名称
    public String name;

    JxStatusEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
