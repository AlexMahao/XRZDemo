package com.mahao.xrzdemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期操作类
 * Created by mdw on 2015/11/12.
 */
public class DateUtils {


    /**
     * 通过日期获取月份英文
     * @param date
     * @return
     */
    public static String getMonth(String date){
        int month = Integer.parseInt(date.substring(5, 7));
        String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};


        return months[month-1]+".";
    }


    /**
     * 通过日期获取天数
     * @param date
     * @return
     */
    public static String getDay(String date){
        return date.substring(8,10);
    }


    /**
     * 获取系统当前时间
     * @return
     */
    public static String getDate(){

        SimpleDateFormat format = new SimpleDateFormat("MM月dd日 hh:mm:ss");

        return format.format(new Date());
    }
}
