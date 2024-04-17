package com.ColumbusEventAlertService.utils;

import org.junit.Assert;
import org.junit.Test;
import java.time.Year;

public class TestDateFormatter {

    @Test
    public void googleDateFormatsCorrecty() {
        DateFormatter dateFormatter = new DateFormatter();
        String expected = "3-5-" + Year.now();
        String actual = dateFormatter.formatGoogleDate("Tue, Mar 5");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void googleDateFormatterHasInvalidDate() {
        DateFormatter dateFormatter = new DateFormatter();
        String expected = "Could not map month: Okt-4-2024";
        String actual = dateFormatter.formatGoogleDate("Tue, Okt 4");

        Assert.assertEquals(expected, actual);
    }
}
