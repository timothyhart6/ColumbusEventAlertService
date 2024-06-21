package com.ColumbusEventAlertService.services;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.utils.EventsUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class TextMessageService {
    private final TwilioService twilioService;
    private final EventsUtil eventsUtil;

    public TextMessageService(TwilioService twilioService, EventsUtil eventsUtil) {
        this.twilioService = twilioService;
        this.eventsUtil = eventsUtil;
    }

    public String sendTodaysEvents() {
        ArrayList<Event> events = eventsUtil.getTodaysEvents();

        String textMessage = getTodaysMessage(events);
        String response = twilioService.sendTwilioText(System.getenv("TESTING_PHONE_NUMBER"),System.getenv("TWILIO_PHONE_NUMBER"), textMessage);

        return response;
    }

    private String getTodaysMessage(ArrayList<Event> events) {
        String message = (events.isEmpty()) ? "No Events today!" : formatTodaysTextMessage(events);

        return message;
    }

    public String formatTodaysTextMessage(ArrayList<Event> events) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TODAY'S EVENTS:\n \n");
                for(Event event: events) {
                    stringBuilder.append(" " + event.getLocationName()+ ":\n" +
                           "  " + event.getEventName() + " at " + event.getTime() + "\n \n");
                }
        return stringBuilder.toString();
    }
}
