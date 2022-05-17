package com.dingar.twok.core.util;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Locale;

public class DateUtil {
    //Singleton
    private DateUtil(){}
    private static DateUtil instance;
    public static DateUtil getInstance(){
        if (instance ==  null)
            instance = new DateUtil();
        return instance;
    }

    public static String timeStampToDate(String timeStamp){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.parseLong(timeStamp));
        return DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();
    }
}
