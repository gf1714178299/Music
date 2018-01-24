package com.gf.musics.web.enums;

/**
 * Created by lokey on 2017/11/12.
 */
public enum OpenTypeEnum {
    //1:qq 2:微信 3:微博
    QQ(1),WECHAT(2),BLOG(3);

    private int value;

    OpenTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
