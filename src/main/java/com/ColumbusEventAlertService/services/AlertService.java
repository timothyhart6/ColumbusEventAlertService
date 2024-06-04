package com.ColumbusEventAlertService.services;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.columbusEvents.EventServiceImpl;
import com.ColumbusEventAlertService.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class AlertService {
    private DateUtil dateUtil;
    private EventServiceImpl eventServiceImpl;
    private TwilioService twilioService;


    public AlertService(DateUtil dateUtil, EventServiceImpl eventServiceImpl, TwilioService twilioService) {
        this.dateUtil = dateUtil;
        this.eventServiceImpl = eventServiceImpl;
        this.twilioService = twilioService;
    }


    public String sendTodaysEvents() {
        ArrayList<Event> events = getTodaysEvents();
        String textMessage = getTodaysMessage(events);
        String response = twilioService.sendTwilioText(System.getenv("TESTING_PHONE_NUMBER"),System.getenv("TWILIO_PHONE_NUMBER"), textMessage);

        return response;
    }

    private String getTodaysMessage(ArrayList<Event> events) {
        String message = (events.isEmpty()) ? "No Events today!" : formatTodaysTextMessage(events);

        return message;
    }

    private ArrayList<Event> getTodaysEvents() {
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

    private ArrayList<Event> getAllEvents() {

        ArrayList<Event> events = new ArrayList<>();

        events.add(eventServiceImpl.getUpcomingEvent());

        return events;
    }

    public String formatTodaysTextMessage(ArrayList<Event> events) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TODAY'S EVENTS:\n");
                for(Event event: events) {
                    stringBuilder.append(" " + event.getLocationName()+ ":\n" +
                           "  " + event.getEventName() + " at " + event.getTime() + "\n");
                }
        return stringBuilder.toString();
    }
}
