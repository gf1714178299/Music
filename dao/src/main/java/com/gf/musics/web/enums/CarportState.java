package com.gf.musics.web.enums;

/**
 * Created by lokey on 2017/11/12.
 */
public enum  CarportState {
    //1:未开放 2 闲置 3在使用 4审核中 5未通过
    NOT_OPEN(1),NOT_USE(2),USE_ING(3),CHECK_ING(4),NOT_CHECKED(5);

    private int value;

    CarportState(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

}
