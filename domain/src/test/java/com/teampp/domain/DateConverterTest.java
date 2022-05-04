package com.teampp.domain;

import com.teampp.domain.teamdate.DateConverter;

import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class DateConverterTest {
    @Test
    public void DateToStringConverterTest() {
        Date date = new Date();
        date.setTime(1220227200L * 1000);
        String expected = "2008-09-01 02:00:00";
        String actual = DateConverter.convertDateToString(date);
        assertEquals(expected, actual);
    }

    @Test
    public void StringToDateConverterTest(){
        Date expected = new Date(1220227200L * 1000);
        String dateString = "2008-09-01 02:00:00";
        Date actual = DateConverter.convertStringToDate(dateString);
        assertEquals(expected, actual);
    }
}
