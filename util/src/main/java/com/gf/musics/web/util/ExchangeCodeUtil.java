package com.gf.musics.web.util;

import java.util.Date;

/**
 * 优惠券兑换码生成
 * 生成规则------（时间戳+随机大写字母）*MD5加密
 * Created by CANONYANG on 2017/12/12.
 */
public class ExchangeCodeUtil {
    public static void main(String[] args) {
        System.out.println(getCode());
    }

    public static long getTimestamp() {
        return new Date().getTime();
    }

    public static String generateRandomStr(int len) {
        //字符源，可以根据需要删减
        String generateSource = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String rtnStr = "";
        for (int i = 0; i < len; i++) {
            //循环随机获得当次字符，并移走选出的字符
            String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
            rtnStr += nowStr;
            generateSource = generateSource.replaceAll(nowStr, "");
        }
        return rtnStr;
    }

    public static String getCode(){
        String code = getTimestamp()+generateRandomStr(10);
        String MD5Code = MD5Util.MD5Encode(code).substring(0,16);
        return MD5Code;
    }
}
