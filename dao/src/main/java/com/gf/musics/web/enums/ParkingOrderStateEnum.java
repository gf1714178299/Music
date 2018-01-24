package com.gf.musics.web.enums;

/**
 * Created by lokey on 2017/11/2.
 */
public enum ParkingOrderStateEnum {
    // 1未停车 2已停车 3 车开走 4已结算
    NOT_IN(1,"未停车"),CAR_IN(2,"已停车"),CAR_OUT(3,"车开走"),ALREADY_BALANCE(4,"已结算");

    private int value;

    private String msg;

    ParkingOrderStateEnum(int value,String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int getValue() {
        return this.value;
    }

    public String getMsg() {
        return this.msg;
    }
}
