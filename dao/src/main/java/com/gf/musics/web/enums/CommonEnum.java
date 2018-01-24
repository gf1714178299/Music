package com.gf.musics.web.enums;

/**
 * Created by lokey on 2017/11/2.
 */
public enum CommonEnum {
    FALSE(0), TURE(1), ELSE(2);

    private int value;

    CommonEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
