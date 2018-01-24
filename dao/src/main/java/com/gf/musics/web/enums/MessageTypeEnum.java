package com.gf.musics.web.enums;

/**
 * Created by lokey on 2017/11/12.
 */
public enum MessageTypeEnum {

    LOCK("STATE_CHANGE"),WARNING("WARNING"),HEART_BEATING("HEART_BEATING"),POWER_ON("POWER_ON");

    private String value;


    MessageTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
