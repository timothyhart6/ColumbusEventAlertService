package com.ColumbusEventAlertService.services;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.smsProviders.TwilioService;
import com.ColumbusEventAlertService.utils.EventsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Slf4j
@Service
public class TextMessageService {
    //TODO Should I be using the interface instead of the implemented class?
    @Autowired
    private TwilioService twilioService;
    @Autowired
    private EventsUtil eventsUtil;

    //Method that sends the Text Message
    public void sendTodaysEvents() {
        log.info("Text Message is sending...");
        ArrayList<Event> events = eventsUtil.getTodaysEvents();
        String textMessage = events.isEmpty() ? "No Events today!" : formatTodaysTextMessage(events);
        twilioService.sendTextMessage(textMessage);
    }

    private String formatTodaysTextMessage(ArrayList<Event> events) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TODAY'S EVENTS:\n \n");
                for(Event event: events) {
                    stringBuilder.append(" " + event.getLocationName()+ ":\n" +
                           "  " + event.getEventName() + " at " + event.getTime() + "\n \n");
                }
        return stringBuilder.toString();
    }
}