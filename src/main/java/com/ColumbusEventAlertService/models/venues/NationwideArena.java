package com.ColumbusEventAlertService.models.venues;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.events.NationwideEventService;
import org.springframework.beans.factory.annotation.Autowired;

public class NationwideArena extends Venue {

    @Autowired
    NationwideEventService nationwideEventService;

    public NationwideArena(String name, String url) {
        super(name, url);
    }

    public Event nextEvent() {
        return nationwideEventService.getNextEvent(name, url);
    }
}