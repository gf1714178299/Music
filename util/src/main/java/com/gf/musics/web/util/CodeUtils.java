package com.gf.musics.web.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by lokey on 2017/11/2.
 */
public class CodeUtils {

    public static void main(String[] args) {
        System.out.println(getToken(1,1));
    }
    public static String createCode() {
        String result = "";
        for (int i = 0; i < 6; i++) {
            result += (int) (Math.random() * 10);
        }
        return result;
    }
    /**
     * 生成订单号
     *
     * @return
     */
    public static String creatOrderNo(Integer type, Long sno){
        Date date = new Date();
        String dateStr = new SimpleDateFormat("yyMMdd").format(date);
        String seconds = new SimpleDateFormat("HHmmss").format(date);
        String snoStr = new DecimalFormat("000000").format(sno);
        String userStr = new DecimalFormat("00").format(type);
        String orderNum = dateStr +  userStr + seconds + snoStr;
        return orderNum;
    }

    public static String getToken(Integer type,Integer id){
        String typeStr = new DecimalFormat("00").format(type);
        String token = UUID.randomUUID().toString().replaceAll("-","");
        String userStr =  new DecimalFormat("00").format(id);
        return typeStr+token+userStr;
    }

    public static String getCarportNo(Integer userId){
        String userStr =  new DecimalFormat("00").format(userId);
        String token = UUID.randomUUID().toString().replaceAll("-","");
        return userStr+token;
    }
}
