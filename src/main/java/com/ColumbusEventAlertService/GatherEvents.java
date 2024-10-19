package com.ColumbusEventAlertService;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Component
public class GatherEvents {
    @Autowired
    NationwideEventService nationwideEventService;
    @Autowired
    LowerFieldEventService lowerFieldEventService;
    @Autowired
    KembaLiveEventService kembaLiveEventService;
    @Autowired
    NewportEventService newportEventService;
    @Autowired
    ArBarEventService arBarEventService;

    @Bean
    public ArrayList<Event> getTodaysEvents() {
        ArrayList<Event> events = getAllEvents();
        ZoneId zone = ZoneId.of("America/New_York");
        String todaysDate = Instant.now().atZone(zone).format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        events.removeIf(event -> (!event.getDate().equals(todaysDate)));

        return events;
    }

    private ArrayList<Event> getAllEvents() {
        ArrayList<Event> events = new ArrayList<>();
        events.addAll(staticEvents());
        events.add(nationwideEventService.getNextEvent());
        events.add(lowerFieldEventService.getNextEvent());
        events.add(kembaLiveEventService.getNextEvent());
        events.add(newportEventService.getNextEvent());
        events.add(arBarEventService.getNextEvent());
        return events;
    }

    private ArrayList<Event> staticEvents() {
        ArrayList<Event> events = new ArrayList<>();
        events.add(Event.builder().locationName("Ohio Stadium").eventName("OSU VS. Nebraska").date("10-08-2024").time("Unkown").build());

        return events;
    }
}