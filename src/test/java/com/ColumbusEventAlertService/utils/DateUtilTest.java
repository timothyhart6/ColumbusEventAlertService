package com.ColumbusEventAlertService.utils;

import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateUtilTest {

    DateUtil subject = new DateUtil();

    @Test
    public void testDateFormat() {
        LocalDate date = LocalDate.of(2020, 1, 12);
        String formattedDate = new DateUtil().formatDate(date);
        Assert.assertEquals("01-12-2020", formattedDate);
    }

    @Test
    public void googleDateFormatsCorrectly() {
        String expected = "03-05-" + Year.now();
        String actual = subject.formatGoogleDate("Tue, Mar 5");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void googleDateFormatterHasInvalidDate() {
        String expected = "Could not map month: Okt-04-2024";
        String actual = subject.formatGoogleDate("Tue, Okt 4");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void singleDigitDayIsFormatted() {
        String actual = subject.formatDay("6");
        assertEquals("06", actual);
    }

    @Test
    public void twoDigitDayIsFormatted() {
        String actual = subject.formatDay("08");
        assertEquals("08", actual);
    }
}
