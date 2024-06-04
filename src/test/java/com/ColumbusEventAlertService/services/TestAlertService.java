package com.ColumbusEventAlertService.services;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.columbusEvents.EventServiceImpl;
import com.ColumbusEventAlertService.utils.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestAlertService {

    @Mock
    private DateUtil dateUtil;
    @Mock
    private EventServiceImpl eventServiceImpl;
    @Mock
    private TwilioService twilioService;
    @InjectMocks
    private AlertService alertService;

    @BeforeEach
    void setUp() {
        Event event = new Event();
        event.setDate("2024-07-04");
        event.setLocationName("Home");
        event.setEventName("TV");
        event.setTime("7:00PM");
        when(eventServiceImpl.getUpcomingEvent()).thenReturn(event);
        when(twilioService.sendTwilioText(anyString(), anyString(), anyString())).thenReturn("Message sent");
    }
    @Test
    void testSendTodaysEvents_WithEventToday() {
        when(dateUtil.getTodaysDate()).thenReturn("2024-07-04");

        String response = alertService.sendTodaysEvents();
        String expected = "TODAY'S EVENTS:\n Home:\n  TV at 7:00PM\n";

        verify(twilioService).sendTwilioText(anyString(), anyString(), eq(expected));
        assertEquals("Message sent", response);

    }
    @Test
    void testSendTodaysEvents_NoEventToday() {
        when(dateUtil.getTodaysDate()).thenReturn("2024-05-05");

        String response = alertService.sendTodaysEvents();

        verify(twilioService).sendTwilioText(anyString(), anyString(), eq("No Events today!"));
        assertEquals("Message sent", response);
    }

    @Test
    void testSendYesterdaysEvents_NoEventToday() {
        when(dateUtil.getTodaysDate()).thenReturn("2024-07-05");

        String response = alertService.sendTodaysEvents();

        verify(twilioService).sendTwilioText(anyString(), anyString(), eq("No Events today!"));
        assertEquals("Message sent", response);
    }

    @Test
    void testSendNextYearEvents_NoEventToday() {
        when(dateUtil.getTodaysDate()).thenReturn("2025-07-04");

        String response = alertService.sendTodaysEvents();

        verify(twilioService).sendTwilioText(anyString(), anyString(), eq("No Events today!"));
        assertEquals("Message sent", response);
    }
}