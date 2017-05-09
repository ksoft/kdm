package com.ksoft.kdm.enums;

public enum DzChannelEnum {

	APP("APP"), WX("微信"),QT("其它");

    //名称
    public String name;

    DzChannelEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
