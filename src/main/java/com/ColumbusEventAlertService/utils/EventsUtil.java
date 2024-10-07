package com.ColumbusEventAlertService.utils;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.models.venues.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Component
public class EventsUtil {

    @Autowired
    DateUtil dateUtil;

    //Venue Names
    private static final String nationwideArenaName = "Nationwide Arena";
    private static final String lowerFieldName = "Lower.com Field";
    private static final String kembaLiveName = "KEMBA Live!";
    private static final String newportName = "Newport Music Hall";
    private static final String arBarName = "A&R Music Bar";

    //Venue Urls
    private static final String nationwideArenaUrl = "https://www.nationwidearena.com/events";
    private final static String lowerFieldUrl = "https://lowerfieldcbus.com/events/";
    private final String kembaLiveUrl = "https://promowestlive.com/columbus/kemba-live";
    private final static String newportUrl = "https://promowestlive.com/columbus/newport-music-hall";
    private final static String arBarUrl = "https://promowestlive.com/columbus/a-and-r-music-bar";

    //Venues
    NationwideArena nationwideArena = new NationwideArena(nationwideArenaName, nationwideArenaUrl);
    LowerField lowerField = new LowerField(lowerFieldName, lowerFieldUrl);
    KembaLive kembaLive = new KembaLive(kembaLiveName, kembaLiveUrl);
    Newport newport = new Newport(newportName, newportUrl);
    ArBar arBar = new ArBar(arBarName, arBarUrl);

    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> events = new ArrayList<>();
        events.add(nationwideArena.nextEvent());
        events.add(lowerField.nextEvent());
        events.add(kembaLive.nextEvent());
        events.add(newport.nextEvent());
        events.add(arBar.nextEvent());
        return events;
    }

    public ArrayList<Event> getTodaysEvents() {
        ArrayList<Event> events = getAllEvents();
        ZoneId zone = ZoneId.of("America/New_York");
        String todaysDate = Instant.now().atZone(zone).format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        events.removeIf(event -> (!event.getDate().equals(todaysDate)));

        return events;
    }
}