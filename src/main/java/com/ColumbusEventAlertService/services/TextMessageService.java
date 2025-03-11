package com.ColumbusEventAlertService.services;

import com.ColumbusEventAlertService.GatherEvents;
import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.smsProviders.TwilioService;
import com.ColumbusEventAlertService.utils.DynamoDBReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TextMessageService {
    @Autowired
    private TwilioService twilioService;
    @Autowired
    private GatherEvents gatherEvents;

    //Method that sends the Text Message
    public void sendTodaysEvents() {
        log.info("Text Message is sending...");
        ArrayList<Event> events = gatherEvents.getTodaysEvents(new DynamoDBReader());
        String textMessage = events.isEmpty() ? "No Events today!" : formatTodaysTextMessage(events);
        twilioService.sendTextMessage(textMessage);
    }

    private String formatTodaysTextMessage(ArrayList<Event> events) {
        ArrayList<Event> badTrafficEvents = new ArrayList<>();
        ArrayList<Event> desiredEvents = new ArrayList<>();

        for(Event event: events) {
            if(event.isBadTraffic()) badTrafficEvents.add(event);
            if(event.isDesiredEvent()) desiredEvents.add(event);
        }

        String badTrafficMessage = getBadTrafficMessage(badTrafficEvents, "%s at %s area around %s");

        String funEventsMessage = getFunEventsMessage(desiredEvents, "%s at %s. %s");

        String completeMessage = """
        Avoid driving here!!
        %s

        These could be fun!
        %s
        """.formatted(badTrafficMessage, funEventsMessage);

        log.info("Formatted Text Message being sent: " + completeMessage);
        return completeMessage;
    }

    private static String getFunEventsMessage(ArrayList<Event> desiredEvents, String format) {
        String funEventsMessage = desiredEvents.stream()
                .map(event -> String.format(format,
                        event.getEventName(), event.getLocationName(), event.getTime()))
                .collect(Collectors.joining("\n"));
        return funEventsMessage;
    }

    private static String getBadTrafficMessage(ArrayList<Event> badTrafficEvents, String format) {
        String badTrafficMessage = badTrafficEvents.stream()
                .map(event -> String.format(format,
                        event.getEventName(), event.getLocationName(), event.getTime()))
                .collect(Collectors.joining("\n"));
        return badTrafficMessage;
    }
}