package com.ColumbusEventAlertService;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.Year;
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
        String todaysYear = Year.now().toString();
        if (todaysYear.equals("2024")) {
            //OSU Football Games
            events.add(Event.builder().locationName("Ohio Stadium").eventName("OSU VS. Nebraska").date("10-26-2024").time("12:00pm").build());
            events.add(Event.builder().locationName("Away Game").eventName("OSU @ Penn State}").date("11-02-2024").time("Unknown").build());
            events.add(Event.builder().locationName("Ohio Stadium").eventName("OSU VS. Purdue").date("11-09-2024").time("Unknown").build());
            events.add(Event.builder().locationName("Away Game").eventName("OSU @ Nothwestern").date("11-16-2024").time("Unknown").build());
            events.add(Event.builder().locationName("Ohio Stadium").eventName("OSU VS. Indiana").date("11-23-2024").time("Unknown").build());
            events.add(Event.builder().locationName("Ohio Stadium").eventName("OSU VS. Michigan").date("11-30-2024").time("Unknown").build());

            events.add(Event.builder().locationName("Columbus").eventName("Nationwide Marathon").date("10-20-2024").time("7:30am-2pm").build());
            events.add(Event.builder().locationName("Short North/Downtown area").eventName("Highball").date("10-26-2024").time("2pm-12am").build());
            events.add(Event.builder().locationName("Short North").eventName("Hops on High").date("12-07-2024").time("12pm-8pm").build());
            events.add(Event.builder().locationName("German Village").eventName("Village Lights").date("12-08-2024").time("9pm").build());
        }
        if(todaysYear.equals("2025")) {
            events.add(Event.builder().locationName("Columbus").eventName("Cap City Marathon").date("04-26-2025").time("8am-1pm").build());

            events.add(Event.builder().locationName("Downtown Riverfront").eventName("Arts Festival").date("06-06-2025").time("All Day").build());
            events.add(Event.builder().locationName("Downtown Riverfront").eventName("Arts Festival").date("06-07-2025").time("All Day").build());
            events.add(Event.builder().locationName("Downtown Riverfront").eventName("Arts Festival").date("06-08-2025").time("All Day").build());

            events.add(Event.builder().locationName("Goodale Park").eventName("Comfest").date("06-27-2025").time("All Day").build());
            events.add(Event.builder().locationName("Goodale Park").eventName("Comfest").date("06-28-2025").time("All Day").build());
            events.add(Event.builder().locationName("Goodale Park").eventName("Comfest").date("06-29-2025").time("All Day").build());
            events.add(Event.builder().locationName("Downtown").eventName("Red, White & Boom").date("07-03-2025").time("All Day").build());

            //TO BE ADDED WHEN DATES ARE AVAILABLE:
            //Tacofest
            //Italianfest
            //State Fair
            //Short North Garage Sale
            //Jazz and Rib Fest
            // The Arnold (check it's included in the convention center searches)
            //Pride
            //Strawberry Jam
        }
        return events;
    }
}