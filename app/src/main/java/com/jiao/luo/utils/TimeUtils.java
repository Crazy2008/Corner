package com.jiao.luo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/10.
 */

public class TimeUtils {


    public static String stmpToDate(long timestmp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time_st_string = format.format(timestmp);
        return time_st_string;
    }

    public static String stmpToTime(long timestmp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time_st_string = format.format(timestmp);
        return time_st_string;
    }
    public static String stmpToDate(String timestmp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time_st_string = format.format(new Date(Long.valueOf(timestmp)*1000L));
        return time_st_string;
    }
    public  static   long getCurrentTiem(){
        long l = System.currentTimeMillis();
        return l;
    }
}
