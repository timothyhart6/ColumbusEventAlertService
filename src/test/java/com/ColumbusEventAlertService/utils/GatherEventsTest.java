package com.ColumbusEventAlertService.utils;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.GatherEvents;
import com.ColumbusEventAlertService.services.events.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Test
    public void testGetTodaysEvents() {
        Event eventToday = new Event("Today!");
        Event eventInThePast = new Event("In the past");

        ZoneId zone = ZoneId.of("America/New_York");
        String todaysDate = Instant.now().atZone(zone).format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        eventToday.setDate(todaysDate);
        eventInThePast.setDate("01-01-1930");

        when(nationwideEventService.getNextEvent()).thenReturn(eventToday);
        when(kembaLiveEventService.getNextEvent()).thenReturn(eventToday);
        when(lowerFieldEventService.getNextEvent()).thenReturn(eventInThePast);
        when(arBarEventService.getNextEvent()).thenReturn(eventInThePast);
        when(newportEventService.getNextEvent()).thenReturn(eventInThePast);

        ArrayList<Event> todaysEvents = gatherEvents.getTodaysEvents();

        assertTrue(todaysEvents.size() > 1);
    }
}