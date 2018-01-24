package com.gf.musics.web.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lokey on 2017/8/29.
 */
public class TimeUtil {

    private static SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static SimpleDateFormat BaseFormat = new SimpleDateFormat("yy-MM-dd HH:mm");

    private static SimpleDateFormat HourFormat = new SimpleDateFormat("HH:mm");

    private static SimpleDateFormat TimeFormat = new SimpleDateFormat("yy-MM-dd HH:mm");


    public static String dateString(Date date) {
        String res;
        res = DateFormat.format(date);
        return res;
    }

    public static String baseString(Date date) {
        String res;
        res = BaseFormat.format(date);
        return res;
    }

    public static String hourString(Date date) {
        String res;
        res = HourFormat.format(date);
        return res;
    }

    public static String timeString(Date date) {
        String res;
        res = TimeFormat.format(date);
        return res;
    }

    public static String parseDate(Date date, String formatString){
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        return format.format(date);
    }

    public static Long getDuration(Date date){
        return  ((new Date()).getTime() - date.getTime())/(1000*60);
    }

    public static Date getDaysLater(Date date,int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+days);
        calendar.set(Calendar.HOUR_OF_DAY,8);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }

    public static Boolean compareDay(Date date,Date date1){
        Calendar nowCal = Calendar.getInstance();
        nowCal.setTime(date1);
        Calendar compareCal = Calendar.getInstance();
        compareCal.setTime(date);
        System.out.println(nowCal.get(Calendar.DAY_OF_MONTH));
        System.out.println(compareCal.get(Calendar.DAY_OF_MONTH));
        if(nowCal.get(Calendar.DAY_OF_MONTH) == compareCal.get(Calendar.DAY_OF_MONTH)){
            return true;
        }
        return  false;
    }

}
