package com.ColumbusEventAlertService;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.events.*;
import com.ColumbusEventAlertService.utils.DynamoDBReader;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
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
        List<Map<String, AttributeValue>> items = dynamoDBReader.getTodaysEvents(DynamoDbClient.create());

        if (items.isEmpty()) {
            return events;
        } else {
            for (Map<String, AttributeValue> item : items) {
                String locationName = nullCheck(item.get("_airbyte_data").m().get("locationName"));
                String eventName = nullCheck(item.get("_airbyte_data").m().get("eventName"));
                String date = nullCheck(item.get("_airbyte_data").m().get("date"));
                String time = nullCheck(item.get("_airbyte_data").m().get("time"));
                boolean createsTraffic = item.get("_airbyte_data").m().get("createsTraffic").bool();
                boolean desiredEvent = item.get("_airbyte_data").m().get("desiredEvent").bool();

                Event event = new Event(locationName, eventName, date, time, createsTraffic, desiredEvent);
                events.add(event);
            }
        }
        return events;
    }

    private static String nullCheck(AttributeValue attribute) {
        return (attribute != null && attribute.s() != null) ? attribute.s() : "";
    }
}