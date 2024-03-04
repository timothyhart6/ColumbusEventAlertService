package com.ColumbusEventAlertService;

import com.ColumbusEventAlertService.models.Event;
import org.junit.Assert;
import org.junit.Test;

public class TestNationwideArenaEvents {


    @Test
    public void nextUpcomingEventIsReturned() {
        NationwideArenaEvents nationwideArenaEvents = new NationwideArenaEvents();
        Event event = nationwideArenaEvents.getUpcomingEvent();

        Assert.assertNotNull(event);
    }

    @Test
    public void nextEventDetailsExist() {
        NationwideArenaEvents nationwideArenaEvents = new NationwideArenaEvents();
        Event event = nationwideArenaEvents.getUpcomingEvent();

        String name = event.getName();
        String date = event.getDate;
        String time = event.getTime;

        Assert.assertTrue(!name.isEmpty());
        Assert.assertTrue(!date.isEmpty());
        Assert.assertTrue(!time.isEmpty());
    }


}
