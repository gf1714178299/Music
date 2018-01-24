package com.gf.musics.web.enums;

/**
 * Created by lokey on 2017/11/2.
 */
public enum OrderTypeEnum {
    //1实时 2预约
    IN_TIME(1),TOMORROW(2);

    private int value;

    OrderTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
