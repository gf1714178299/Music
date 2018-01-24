package com.gf.musics.web.enums;

/**
 * Created by lokey on 2017/11/2.
 */
public enum ChannelEnum {
    //支付通道
    ACCOUNT(0,"余额"),ALIPAY(1,"alipay"),WECHAT(2,"wechat");

    private int value;

    private String msg;

    ChannelEnum(int value,String msg) {
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
