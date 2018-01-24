package com.gf.musics.web.enums;

/**
 * Created by lokey on 2017/11/2.
 */
public enum ParkingChargeTypeEnum {
    //1预付 2结算
    PREPAY(1),BALANCE(2);

    private int value;

    ParkingChargeTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
