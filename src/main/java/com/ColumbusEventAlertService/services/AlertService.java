package com.ColumbusEventAlertService.services;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.columbusEvents.NationwideArenaService;
import com.ColumbusEventAlertService.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class AlertService {
    private DateUtil dateUtil;
    private NationwideArenaService nationwideArenaService;
    private TwilioService twilioService;


    public AlertService(DateUtil dateUtil, NationwideArenaService nationwideArenaService, TwilioService twilioService) {
        this.dateUtil = dateUtil;
        this.nationwideArenaService = nationwideArenaService;
        this.twilioService = twilioService;
    }


    public String sendTodaysEvents() {
        ArrayList<Event> events = getTodaysEvents();
        String textMessage = getTodaysMessage(events);
        String response = twilioService.sendTwilioText(System.getenv("TESTING_PHONE_NUMBER"),System.getenv("TWILIO_PHONE_NUMBER"), textMessage);

        return response;
    }

    private String getTodaysMessage(ArrayList<Event> events) {
        String message = (!events.isEmpty()) ? "Events today!" : "No Events today!";

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

        events.add(nationwideArenaService.getUpcomingEvent());

        return events;
    }
}
