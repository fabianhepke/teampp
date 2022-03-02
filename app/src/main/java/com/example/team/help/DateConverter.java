package com.example.team.help;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateConverter {

    public static String convertDateToString(Date date) {
        String dateString = "";
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        calendar.setTime(date);
        dateString += calendar.get(Calendar.YEAR) + "-";
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month < 10) {
            dateString += "0" + month + "-";
        }
        else {
            dateString += (calendar.get(Calendar.MONTH) + 1) +"-";
        }
        int day =calendar.get(Calendar.DAY_OF_MONTH);
        if (day < 10) {
            dateString += "0" + day + " ";
        }
        else {
            dateString += day + " ";
        }
        int hour = calendar.get(Calendar.HOUR);
        if (hour < 10) {
            dateString += "0" + hour + ":";
        }
        else {
            dateString += hour + ":";
        }
        int min = calendar.get(Calendar.MINUTE);
        if (min < 10) {
            dateString += "0" + min + ":00";
        }
        else {
            dateString += min +":00";
        }
        return dateString;
    }
}
