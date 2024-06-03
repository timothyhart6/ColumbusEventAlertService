package com.ColumbusEventAlertService.services;

import com.ColumbusEventAlertService.services.columbusEvents.NationwideArenaService;
import com.ColumbusEventAlertService.models.NationwideEvent;
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
    private NationwideArenaService nationwideArenaService;
    @Mock
    private TwilioService twilioService;
    @InjectMocks
    private AlertService alertService;

    @BeforeEach
    void setUp() {
        NationwideEvent event = new NationwideEvent();
        event.setDate("2024-07-04");
        when(nationwideArenaService.getUpcomingEvent()).thenReturn(event);
        when(twilioService.sendTwilioText(anyString(), anyString(), anyString())).thenReturn("Message sent");
    }
    @Test
    void testSendTodaysEvents_WithEventToday() {
        when(dateUtil.getTodaysDate()).thenReturn("2024-07-04");

        String response = alertService.sendTodaysEvents();

        verify(twilioService).sendTwilioText(anyString(), anyString(), eq("Events today!"));
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