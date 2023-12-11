package com.mwg.tms.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDate {
    public static String format(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String _date = simpleDateFormat.format(new Date());
        return _date;
    }

    public static String format(Date date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String _date = simpleDateFormat.format(date);
        return _date;
    }
}
