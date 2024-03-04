package com.ColumbusEventAlertService;

import com.ColumbusEventAlertService.models.Event;

public class NationwideArenaEvents {

    public Event getUpcomingEvent() {
        Event event = new Event();
        event.setName("Guy Fieri - Columbus");
        event.setGetDate("Mon, Mar 4");
        event.setGetTime("7:00PM");
        return event;
    }
}
