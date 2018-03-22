package com.example.atlas.cscc20.MyTool;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取系统当前时间和日期
 */

public class DateTool {
    public static String getCurrentDate(){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy / MM / dd");
        String date_text = dateFormat.format(date);
        return date_text;
    }
}
