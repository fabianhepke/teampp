package com.example.team;

import com.example.team.help.DateConverter;

import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class DateConverterTest {
    @Test
    public void DateConverterTest() {
        Date date = new Date();
        date.setTime(1220227200L * 1000);
        String exepted = "2008-09-01 02:00:00";
        String actual = DateConverter.convertDateToString(date);
        assertEquals(exepted, actual);
    }
}
