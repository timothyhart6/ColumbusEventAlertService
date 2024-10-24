package com.ColumbusEventAlertService.services;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.smsProviders.TwilioService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Slf4j
@Service
public class TextMessageService {
    @Autowired
    private TwilioService twilioService;
    @Autowired
    private GatherEvents gatherEvents;

    @Bean
    //Method that sends the Text Message
    public void sendTodaysEvents() {
        log.info("Text Message is sending...");
        ArrayList<Event> events = gatherEvents.getTodaysEvents();
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