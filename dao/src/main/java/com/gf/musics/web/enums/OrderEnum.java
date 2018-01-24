package com.gf.musics.web.enums;

/**
 * Created by lokey on 2017/11/2.
 */
public enum OrderEnum {
    //1 定金订单
    BONDORDER(1),CHARGEORDER(2),PARKINGORDER(3);

    private int value;

    OrderEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
