package com.gf.musics.web.enums;

/**
 * Created by lokey on 2017/11/2.
 */
public enum LockTypeEnum {
    //0关闭1打开
    OPEN(1,"放倒车锁"),CLOSE(2,"升起车锁"),ERROR(3,"车锁故障");

    private int value;

    private String msg;

    LockTypeEnum(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }
}
