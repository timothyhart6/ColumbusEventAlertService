package com.ColumbusEventAlertService;

import com.ColumbusEventAlertService.models.Event;
import org.jsoup.helper.ValidationException;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;

public class TestNationwideArenaEvents {
    NationwideArenaEvents nationwideArenaEvents = new NationwideArenaEvents();

    @Test
    public void nextEventDetailsExist() throws ValidationException {
        String googleUrl = "https://www.google.com/search?q=nationwide+arena+events&gl=us";
        Event event = nationwideArenaEvents.getUpcomingEvent(googleUrl);

        String name = event.getName();
        String date = event.getDate();
        String time = event.getTime();

        Assert.assertFalse(name.isEmpty());
        Assert.assertFalse(date.isEmpty());
        Assert.assertFalse(time.isEmpty());
    }

    @Test
    public void IllegalArgumentExceptionThrownWhenInvalidUrlIsUsed() throws IllegalArgumentException {
        String googleUrl = "gibberish";
        NationwideArenaEvents mockNationwideAreanaEvents = mock(NationwideArenaEvents.class);
        willThrow(new IllegalArgumentException("No Event")).given(mockNationwideAreanaEvents).getUpcomingEvent(googleUrl);
        IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> nationwideArenaEvents.getUpcomingEvent(googleUrl));
        Assert.assertEquals("Invalid URL: gibberish", exception.getMessage());
    }
}
