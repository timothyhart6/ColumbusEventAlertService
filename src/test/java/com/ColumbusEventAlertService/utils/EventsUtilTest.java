package com.ColumbusEventAlertService.utils;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.columbusEvents.JsoupServiceImpl;
import com.ColumbusEventAlertService.services.columbusEvents.KembaLiveEventServiceImpl;
import com.ColumbusEventAlertService.services.columbusEvents.NationwideEventServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class EventsUtilTest {

    @Mock
    private JsoupServiceImpl jsoupService;

    @Mock
    private NationwideEventServiceImpl nationwideEventsService;

    @Mock
    private KembaLiveEventServiceImpl kembaLiveEventService;

    @InjectMocks
    @Spy
    private EventsUtil EventsUtilSpy;

    @Test
    public void testGetAllEvents() {
        ArrayList<Event> events = EventsUtilSpy.getAllEvents();

        assertEquals(3, events.size());
        assertThat(events.get(0), instanceOf(Event.class));
        assertThat(events.get(1), instanceOf(Event.class));
    }

    @Test
    public void testGetTodaysEvents() {
        DateUtil dateUtil = new DateUtil();
        String todayDate = dateUtil.getTodaysDate();

        Event nationwideEvent = new Event();
        nationwideEvent.setDate(todayDate);
        Event kembaLiveEvent = new Event();

        ArrayList<Event> allEvents = new ArrayList<>();
        allEvents.add(nationwideEvent);
        allEvents.add(kembaLiveEvent);

        doReturn(allEvents).when(EventsUtilSpy).getAllEvents();

        ArrayList<Event> todaysEvents = EventsUtilSpy.getTodaysEvents();

        assertEquals(1, todaysEvents.size());
    }
}