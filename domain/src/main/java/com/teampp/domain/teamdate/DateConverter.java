package com.teampp.domain.teamdate;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    public static String convertDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static Date convertStringToDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = formatter.parse(dateString);

        } catch (ParseException e) {
            Log.e("DateConverter: ",  "convertStringToDate: cannot convert String to Date", e);
        }
        return date;
    }
}
