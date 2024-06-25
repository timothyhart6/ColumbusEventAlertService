package com.ColumbusEventAlertService.utils;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.columbusEvents.*;

import java.util.ArrayList;

public class EventsUtil {

   private static String nationwideUrl = "https://www.nationwidearena.com/events";
   private static String kembaLiveUrl = "https://promowestlive.com/columbus/kemba-live";
   private static String arBarUrl = "https://promowestlive.com/columbus/a-and-r-music-bar";
   private static String newportUrl = "https://promowestlive.com/columbus/newport-music-hall";
   private static String lowerFieldUrl = "https://lowerfieldcbus.com/events/";
   private static JsoupServiceImpl jsoupService = new JsoupServiceImpl();
   private static DateUtil dateUtil = new DateUtil();
   private static NationwideEventServiceImpl nationwideEventsService = new NationwideEventServiceImpl(nationwideUrl, jsoupService, dateUtil, "Nationwide Arena");
   private static KembaLiveEventServiceImpl kembaLiveEventService = new KembaLiveEventServiceImpl(kembaLiveUrl, jsoupService, dateUtil, "Kemba Live");
   private static ArBarEventServiceImpl arBarEventService = new ArBarEventServiceImpl(arBarUrl, jsoupService, dateUtil,"A&R Bar");
   private static NewportEventServiceImpl newportEventService = new NewportEventServiceImpl(newportUrl, jsoupService, dateUtil, "Newport Music Hall");
   private static LowerFieldEventServiceImpl lowerFieldEventService = new LowerFieldEventServiceImpl(lowerFieldUrl, jsoupService, dateUtil, "Lower.com Field");
    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> events = new ArrayList<>();
        events.add(nationwideEventsService.getUpcomingEvent());
        events.add(kembaLiveEventService.getUpcomingEvent());
        events.add(arBarEventService.getUpcomingEvent());
        events.add(newportEventService.getUpcomingEvent());
        events.add(lowerFieldEventService.getUpcomingEvent());
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
