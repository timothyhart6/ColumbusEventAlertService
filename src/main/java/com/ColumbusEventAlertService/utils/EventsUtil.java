package com.ColumbusEventAlertService.utils;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.columbusEvents.ArBarEventServiceImpl;
import com.ColumbusEventAlertService.services.columbusEvents.JsoupServiceImpl;
import com.ColumbusEventAlertService.services.columbusEvents.KembaLiveEventServiceImpl;
import com.ColumbusEventAlertService.services.columbusEvents.NationwideEventServiceImpl;

import java.util.ArrayList;

public class EventsUtil {

    static String nationwideUrl = "https://www.nationwidearena.com/events";
    static String kembaLiveUrl = "https://promowestlive.com/columbus/kemba-live";
    static String arBarUrl = "https://promowestlive.com/columbus/a-and-r-music-bar";
    static JsoupServiceImpl jsoupService = new JsoupServiceImpl();
    static DateUtil dateUtil = new DateUtil();
    static NationwideEventServiceImpl nationwideEventsService = new NationwideEventServiceImpl(nationwideUrl, jsoupService, dateUtil, "Nationwide Arena");
    static KembaLiveEventServiceImpl kembaLiveEventService = new KembaLiveEventServiceImpl(kembaLiveUrl, jsoupService, dateUtil, "Kemba Live");
    static ArBarEventServiceImpl arBarEventService = new ArBarEventServiceImpl(arBarUrl, jsoupService, dateUtil,"A&R Bar");
    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> events = new ArrayList<>();
        events.add(nationwideEventsService.getUpcomingEvent());
        events.add(kembaLiveEventService.getUpcomingEvent());
        events.add(arBarEventService.getUpcomingEvent());
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
