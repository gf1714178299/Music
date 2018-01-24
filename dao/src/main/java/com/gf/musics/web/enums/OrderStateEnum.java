package com.gf.musics.web.enums;

/**
 * Created by lokey on 2017/11/2.
 */
public enum OrderStateEnum {
    NOT_PAY(1), DEALING(2), SUCCESS(3), REFUND_APPLICATION(4),
    REFUND_SUCCESS(5), CANCEL(6), TIMEOUT(7);

    private int value;

    OrderStateEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
