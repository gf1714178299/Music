package com.gf.musics.web.enums;

/**
 * Created by lokey on 2017/11/2.
 */
public enum TokenType {
    //1用户 2工人
    USER(1),WORKER(2);

    private int value;

    TokenType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
