package com.dingar.twok.core.util;

import android.text.format.DateFormat;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    //change timestamp to corresponding date
    public static String timeStampToDate(String timeStamp) throws Exception{
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.parseLong(timeStamp));
        return DateFormat.format("dd-MM-yyyy hh:mm a", cal).toString();
    }

    public static long dateToTimestamp(String dateString) throws Exception{
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
        Date date = (Date)formatter.parse(dateString);
        assert date != null;
        return date.getTime();
    }
}
