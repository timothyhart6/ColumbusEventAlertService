package com.ColumbusEventAlertService.utils;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.columbusEvents.*;

import java.util.ArrayList;

public class EventsUtil {

    static String nationwideUrl = "https://www.nationwidearena.com/events";
    static String kembaLiveUrl = "https://promowestlive.com/columbus/kemba-live";
    static String arBarUrl = "https://promowestlive.com/columbus/a-and-r-music-bar";
    static String newportUrl = "https://promowestlive.com/columbus/newport-music-hall";
    static JsoupServiceImpl jsoupService = new JsoupServiceImpl();
    static DateUtil dateUtil = new DateUtil();
    static NationwideEventServiceImpl nationwideEventsService = new NationwideEventServiceImpl(nationwideUrl, jsoupService, dateUtil, "Nationwide Arena");
    static KembaLiveEventServiceImpl kembaLiveEventService = new KembaLiveEventServiceImpl(kembaLiveUrl, jsoupService, dateUtil, "Kemba Live");
    static ArBarEventServiceImpl arBarEventService = new ArBarEventServiceImpl(arBarUrl, jsoupService, dateUtil,"A&R Bar");
    static NewportEventServiceImpl newportEventService = new NewportEventServiceImpl(newportUrl, jsoupService, dateUtil, "Newport Music Hall");

    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> events = new ArrayList<>();
        events.add(nationwideEventsService.getUpcomingEvent());
        events.add(kembaLiveEventService.getUpcomingEvent());
        events.add(arBarEventService.getUpcomingEvent());
        events.add(newportEventService.getUpcomingEvent());
        return events;
    }

    public ArrayList<Event> getTodaysEvents() {
        ArrayList<Event> allEvents = getAllEvents();
        ArrayList<Event> todaysEvents = new ArrayList<>();
        String todaysDate = dateUtil.getTodaysDate();
        if(!allEvents.isEmpty()) {
            for (Event event: allEvents) {
                if (todaysDate.equals(event.getDate())) {
                    todaysEvents.add(event);
                }
            }
        }
        return todaysEvents;
    }
}
