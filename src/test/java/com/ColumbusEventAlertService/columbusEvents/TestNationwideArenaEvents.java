package com.ColumbusEventAlertService.columbusEvents;

import com.ColumbusEventAlertService.models.Event;
import org.jsoup.helper.ValidationException;
import org.jsoup.nodes.Node;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.*;

public class TestNationwideArenaEvents {
    NationwideArenaEvents nationwideArenaEvents = new NationwideArenaEvents();

    @Test
    public void nextEventDetailsExist() throws ValidationException {
        Event event = nationwideArenaEvents.getUpcomingEvent();

        String name = event.getName();
        String date = event.getDate();
        String time = event.getTime();

        Assert.assertFalse(name.isEmpty());
        Assert.assertFalse(date.isEmpty());
        Assert.assertFalse(time.isEmpty());
    }

    @Test
    public void nextEventDoesNotHaveATime() throws ValidationException {
        Node node = mock(org.jsoup.nodes.Node.class, RETURNS_DEEP_STUBS);
        when(node.childNode(1).childNode(1).childNode(0).toString().trim()).thenThrow(IndexOutOfBoundsException.class);
        Event event = nationwideArenaEvents.getUpcomingEvent();
        String time = event.getTime();

        Assert.assertFalse(time == "UNKNOWN");
    }

    @Test
    public void IllegalArgumentExceptionThrownWhenInvalidUrlIsUsed() throws IllegalArgumentException {
        nationwideArenaEvents.setGoogleUrl("gibberish");
        NationwideArenaEvents mockNationwideAreanaEvents = mock(NationwideArenaEvents.class);
        willThrow(new IllegalArgumentException()).given(mockNationwideAreanaEvents).getUpcomingEvent();
        IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> nationwideArenaEvents.getUpcomingEvent());
        Assert.assertEquals("Invalid URL: gibberish", exception.getMessage());
    }
}