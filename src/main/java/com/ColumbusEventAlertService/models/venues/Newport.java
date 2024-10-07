package com.ColumbusEventAlertService.models.venues;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.events.NewportEventService;
import org.springframework.beans.factory.annotation.Autowired;

public class Newport extends Venue{

    @Autowired
    NewportEventService newportEventService;
    public Newport(String name, String url) {
        super(name, url);
    }

    public Event nextEvent() {
        return newportEventService.getNextEvent(name, url);
    }
}