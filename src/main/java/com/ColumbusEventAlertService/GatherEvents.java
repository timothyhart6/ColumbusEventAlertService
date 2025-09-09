package com.ColumbusEventAlertService;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.events.*;
import com.ColumbusEventAlertService.utils.DynamoDBReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GatherEvents {
    @Autowired
    NationwideEventService nationwideEventService;
    @Autowired
    LowerFieldEventService lowerFieldEventService;
    @Autowired
    KembaLiveEventService kembaLiveEventService;
    @Autowired
    NewportEventService newportEventService;
    @Autowired
    ArBarEventService arBarEventService;
    @Autowired
    AceOfCupsEventService aceOfCupsEventService;

    public ArrayList<Event> getTodaysEvents(DynamoDBReader dynamoDBReader) {
        ArrayList<Event> events = getAllEvents(dynamoDBReader);
        ZoneId zone = ZoneId.of("America/New_York");
        String todaysDate = Instant.now().atZone(zone).format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        events.removeIf(event -> (!event.getDate().equals(todaysDate)));

        return events;
    }

    private ArrayList<Event> getAllEvents(DynamoDBReader dynamoDBReader) {
        ArrayList<Event> events = new ArrayList<>();
        events.addAll(getTodaysEventsFromDatabase(dynamoDBReader));
        events.add(nationwideEventService.getNextEvent());
        events.add(lowerFieldEventService.getNextEvent());
        events.add(kembaLiveEventService.getNextEvent());
        events.add(newportEventService.getNextEvent());
        events.add(arBarEventService.getNextEvent());
        events.add(aceOfCupsEventService.getNextEvent());
        return events;
    }

    public ArrayList<Event> getTodaysEventsFromDatabase(DynamoDBReader dynamoDBReader) {
        ArrayList<Event> events = new ArrayList<>();
        
        // Skip DynamoDB calls when running locally
        if (isRunningLocally()) {
            return events;
        }
        
        List<Map<String, AttributeValue>> items = dynamoDBReader.getTodaysEvents(DynamoDbClientFactory.createClient());

        if (items.isEmpty()) {
            return events;
        } else {
            for (Map<String, AttributeValue> item : items) {
                Map<String, AttributeValue> data = item.get("_airbyte_data").m();
                String locationName = nullCheck(data.get("locationName"));
                String eventName = nullCheck(data.get("eventName"));
                String date = nullCheck(data.get("date"));
                String time = nullCheck(data.get("time"));
                boolean createsTraffic = nullCheckBool(data.get("createsTraffic"));
                boolean desiredEvent = nullCheckBool(data.get("desiredEvent"));


                Event event = new Event(locationName, eventName, date, time, createsTraffic, desiredEvent);
                events.add(event);
            }
        }
        return events;
    }

    private static String nullCheck(AttributeValue attribute) {
        return (attribute != null && attribute.s() != null) ? attribute.s() : "";
    }

    private static boolean nullCheckBool(AttributeValue attribute) {
        return attribute != null && attribute.bool() != null ? attribute.bool() : true;
    }
    
    private static boolean isRunningLocally() {
        return System.getenv("AWS_EXECUTION_ENV") == null;
    }

}