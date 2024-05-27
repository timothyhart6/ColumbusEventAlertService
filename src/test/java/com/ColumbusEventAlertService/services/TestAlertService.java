package com.ColumbusEventAlertService.services;

import com.ColumbusEventAlertService.services.columbusEvents.NationwideArenaService;
import com.ColumbusEventAlertService.models.NationwideEvent;
import com.ColumbusEventAlertService.utils.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestAlertService {

    @Mock
    private DateUtil dateUtil;
    @Mock
    private NationwideArenaService nationwideArenaService;
    @Mock
    private NationwideEvent nationwideEvent;
    @Mock
    private TwilioService twilioService;
    private AlertService alertService;

    @BeforeEach
    void setUp() {
        alertService = new AlertService(dateUtil, nationwideArenaService, nationwideEvent, twilioService);
    }
    @Test
    void testSendTodaysEvents_WithEventToday() {
        String todaysDate = "2024-05-21";
        when(dateUtil.getTodaysDate()).thenReturn(todaysDate);
        when(nationwideArenaService.getUpcomingEvent()).thenReturn(nationwideEvent);
        when(nationwideEvent.getDate()).thenReturn(todaysDate);
        when(nationwideEvent.message()).thenReturn("does not matter");
        when(twilioService.sendTwilioText(anyString(), anyString(), anyString())).thenReturn("Event today!");

        alertService.sendTodaysEvents();

        verify(nationwideEvent).message();
        verify(twilioService).sendTwilioText(anyString(), anyString(), anyString());
    }
    @Test
    void testSendTodaysEvents_NoEventToday() {
        String todaysDate = "2024-05-21";
        String eventDate = "2024-05-22";
        when(dateUtil.getTodaysDate()).thenReturn(todaysDate);
        when(nationwideArenaService.getUpcomingEvent()).thenReturn(nationwideEvent);
        when(nationwideEvent.getDate()).thenReturn(eventDate);
        when(twilioService.sendTwilioText(anyString(), anyString(), anyString())).thenReturn("No Events!");

        alertService.sendTodaysEvents();

        verify(nationwideEvent, times(0)).message();
        verify(twilioService).sendTwilioText(anyString(), anyString(), anyString());
    }

    @Test
    void testSendYesterdaysEvents_NoEventToday() {
        String todaysDate = "2024-05-21";
        String eventDate = "2024-05-20";
        when(dateUtil.getTodaysDate()).thenReturn(todaysDate);
        when(nationwideArenaService.getUpcomingEvent()).thenReturn(nationwideEvent);
        when(nationwideEvent.getDate()).thenReturn(eventDate);
        when(twilioService.sendTwilioText(anyString(), anyString(), anyString())).thenReturn("No Events!");

        alertService.sendTodaysEvents();

        verify(nationwideEvent, times(0)).message();
        verify(twilioService).sendTwilioText(anyString(), anyString(), anyString());
    }
}