package com.ColumbusEventAlertService.services;

import com.ColumbusEventAlertService.columbusEvents.NationwideArenaEvents;
import com.ColumbusEventAlertService.models.NationwideEvent;
import com.ColumbusEventAlertService.utils.DateUtil;
import com.twilio.rest.api.v2010.account.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TestAlertService {

    @Mock
    private DateUtil dateUtil;
    @Mock
    private NationwideArenaEvents nationwideArenaEvents;
    @Mock
    private NationwideEvent nationwideEvent;

    private TwilioService twilioService = new TwilioService();
    @Mock
    private Message message;

    private AlertService alertService;

    @BeforeEach
    void setUp() {
        alertService = new AlertService(dateUtil, nationwideArenaEvents, nationwideEvent, twilioService);
    }
    @Test
    void testSendTodaysEvents_WithEventToday() {
        String todaysDate = "2024-05-21";
        when(dateUtil.getTodaysDate()).thenReturn(todaysDate);
        when(nationwideArenaEvents.getUpcomingEvent()).thenReturn(nationwideEvent);
        when(nationwideEvent.getDate()).thenReturn(todaysDate);
        when(nationwideEvent.message()).thenReturn("Event is happening today!");
        when(message.getBody()).thenReturn("Event is happening today!");

        String result = alertService.sendTodaysEvents();

        assertEquals("Sent from your Twilio trial account - Event is happening today!", result);
    }

    @Test
    void testSendTodaysEvents_NoEventToday() {
        String todaysDate = "2024-05-21";
        String eventDate = "2024-05-22";
        when(dateUtil.getTodaysDate()).thenReturn(todaysDate);
        when(nationwideArenaEvents.getUpcomingEvent()).thenReturn(nationwideEvent);
        when(nationwideEvent.getDate()).thenReturn(eventDate);

        String result = alertService.sendTodaysEvents();

        assertEquals("Sent from your Twilio trial account - No Events today!", result);
    }
}