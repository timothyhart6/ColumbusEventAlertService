package com.ColumbusEventAlertService.utils;

import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import java.time.Year;

public class TestDateUtil {

    @Test
    public void testDateFormat() {
        LocalDate date = LocalDate.of(2020, 1, 12);
        String formattedDate = new DateUtil().formatDate(date);
        Assert.assertEquals("01-12-2020", formattedDate);
    }

    @Test
    public void googleDateFormatsCorrecty() {
        DateUtil dateUtil = new DateUtil();
        String expected = "03-05-" + Year.now();
        String actual = dateUtil.formatGoogleDate("Tue, Mar 5");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void googleDateFormatterHasInvalidDate() {
        DateUtil dateFormatter = new DateUtil();
        String expected = "Could not map month: Okt-04-2024";
        String actual = dateFormatter.formatGoogleDate("Tue, Okt 4");

        Assert.assertEquals(expected, actual);
    }
}
