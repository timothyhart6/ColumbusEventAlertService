package com.ColumbusEventAlertService.utils;

import com.ColumbusEventAlertService.models.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class EventsUtilTest {
    @Spy
    private EventsUtil EventsUtilSpy;

    @Test
    public void testGetTodaysEvents() {
        ZoneId zone = ZoneId.of("America/New_York");
        String todaysDate = Instant.now().atZone(zone).format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        Event nationwideEvent = new Event("Nationwide Arena");
        Event kembaLiveEvent = new Event("Kemba Live");
        nationwideEvent.setDate(todaysDate);
        kembaLiveEvent.setDate("01-01-1930");
        ArrayList<Event> allEvents = new ArrayList<>();
        allEvents.add(nationwideEvent);
        allEvents.add(kembaLiveEvent);
        doReturn(allEvents).when(EventsUtilSpy).getAllEvents();

        ArrayList<Event> todaysEvents = EventsUtilSpy.getTodaysEvents();

        assertEquals(1, todaysEvents.size());
    }
}