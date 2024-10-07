package com.ColumbusEventAlertService.models.venues;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.events.LowerFieldEventService;
import org.springframework.beans.factory.annotation.Autowired;

public class LowerField extends Venue{

    @Autowired
    LowerFieldEventService lowerFieldEventService;
    public LowerField(String name, String url) {
        super(name, url);
    }

    public Event nextEvent() {
        return lowerFieldEventService.getNextEvent(name, url);
    }
}