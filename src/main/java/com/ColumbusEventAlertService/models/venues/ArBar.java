package com.ColumbusEventAlertService.models.venues;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.events.ArBarEventService;
import org.springframework.beans.factory.annotation.Autowired;

public class ArBar extends Venue {

    @Autowired
    ArBarEventService arBarEventService;

    public ArBar(String name, String url) {
        super(name, url);
    }

    public Event nextEvent() {
        return arBarEventService.getNextEvent(name, url);
    }
}