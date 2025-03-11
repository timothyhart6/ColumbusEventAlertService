package com.ColumbusEventAlertService;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.events.*;
import com.ColumbusEventAlertService.utils.DynamoDBReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GatherEventsTest {
    @InjectMocks
    private GatherEvents gatherEvents;
    @Mock
    NationwideEventService nationwideEventService;
    @Mock
    LowerFieldEventService lowerFieldEventService;
    @Mock
    KembaLiveEventService kembaLiveEventService;
    @Mock
    NewportEventService newportEventService;
    @Mock
    ArBarEventService arBarEventService;
    @Mock
    AceOfCupsEventService aceOfCupsEventService;
    @Mock
    DynamoDBReader dynamoDBReader;

    private String todaysDate;

    @BeforeEach
    public void setUp() {
        ZoneId zone = ZoneId.of("America/New_York");
        todaysDate = Instant.now().atZone(zone).format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
    }
    @Test
    public void testGetTodaysEvents() {
        Event eventToday = new Event("Today!");
        Event eventInThePast = new Event("In the past");
        eventToday.setDate(todaysDate);
        eventInThePast.setDate("01-01-1930");

        when(nationwideEventService.getNextEvent()).thenReturn(eventToday);
        when(kembaLiveEventService.getNextEvent()).thenReturn(eventToday);
        when(lowerFieldEventService.getNextEvent()).thenReturn(eventInThePast);
        when(arBarEventService.getNextEvent()).thenReturn(eventInThePast);
        when(newportEventService.getNextEvent()).thenReturn(eventInThePast);
        when(aceOfCupsEventService.getNextEvent()).thenReturn(eventToday);
        when(dynamoDBReader.getTodaysEvents(any())).thenReturn(Collections.emptyList());

        ArrayList<Event> todaysEvents = gatherEvents.getTodaysEvents(dynamoDBReader);

        assertEquals(3, todaysEvents.size());
    }

    @Test
    public void databaseEventsMapToArrayList() {
        List<Map<String, AttributeValue>> events = new ArrayList<>();
        Map<String, AttributeValue> event = Map.of(
                "_airbyte_data", AttributeValue.fromM(Map.of(
                        "date", AttributeValue.fromS(todaysDate),
                        "locationName", AttributeValue.fromS("Short North"),
                        "eventName", AttributeValue.fromS("Doo Dah Parade"),
                        "time", AttributeValue.fromS("12:00 PM"),
                        "createsTraffic", AttributeValue.fromBool(true),
                        "desiredEvent", AttributeValue.fromBool(false)
                ))
        );
        events.add(event);
        when(dynamoDBReader.getTodaysEvents(any())).thenReturn(events);

        ArrayList<Event> todaysEvents = gatherEvents.getTodaysEventsFromDatabase(dynamoDBReader);

        assertEquals(1, todaysEvents.size());
        Event retrievedEvent = todaysEvents.get(0);

        // Additional assertions to validate the new fields
        assertEquals("Short North", retrievedEvent.getLocationName());
        assertEquals("Doo Dah Parade", retrievedEvent.getEventName());
        assertEquals(todaysDate, retrievedEvent.getDate());
        assertEquals("12:00 PM", retrievedEvent.getTime());
        assertTrue(retrievedEvent.isBadTraffic());
        assertFalse(retrievedEvent.isDesiredEvent());
    }


    @Test
    public void noEventsAddedWhenListIsEmpty() {
        List<Map<String, AttributeValue>> events = new ArrayList<>();
        when(dynamoDBReader.getTodaysEvents(any())).thenReturn(events);

        ArrayList<Event> todaysEvents = gatherEvents.getTodaysEventsFromDatabase(dynamoDBReader);

        assertEquals(0, todaysEvents.size());
    }
}