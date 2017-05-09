package com.zcckj.storeshow.enums;

public enum ReadFlagEnum {

	N("未读"), Y("已读");

    //名称
    public String name;

    ReadFlagEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
