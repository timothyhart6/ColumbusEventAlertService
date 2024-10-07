package com.ColumbusEventAlertService.models.venues;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.events.KembaLiveEventService;
import org.springframework.beans.factory.annotation.Autowired;

public class KembaLive extends Venue{

    @Autowired
    KembaLiveEventService kembaLiveEventService;
    public KembaLive(String name, String url) {
        super(name, url);
    }

    public Event nextEvent() {
        return kembaLiveEventService.getNextEvent(name, url);
    }
}