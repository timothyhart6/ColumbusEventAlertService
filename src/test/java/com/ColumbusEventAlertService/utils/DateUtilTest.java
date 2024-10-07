package com.ColumbusEventAlertService.utils;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateUtilTest {


    @Test
    public void singleDigitDayIsFormatted() {
        DateUtil subject = new DateUtil();

        String actual = subject.formatDay("6");

        assertEquals("06", actual);
    }

    @Test
    public void twoDigitDayIsFormatted() {
        DateUtil subject = new DateUtil();

        String actual = subject.formatDay("08");

        assertEquals("08", actual);
    }
}