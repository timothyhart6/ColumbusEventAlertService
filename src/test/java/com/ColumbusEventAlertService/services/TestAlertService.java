package com.ColumbusEventAlertService.services;

import com.ColumbusEventAlertService.columbusEvents.NationwideArenaEvents;
import com.ColumbusEventAlertService.models.NationwideEvent;
import com.ColumbusEventAlertService.utils.DateUtil;
import com.twilio.rest.api.v2010.account.Message;
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
    private NationwideArenaEvents nationwideArenaEvents;
    @Mock
    private NationwideEvent nationwideEvent;
    @Mock
    private TwilioService twilioService;
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
        when(nationwideEvent.message()).thenReturn("does not matter");
        when(twilioService.sendTwilioText(anyString(), anyString())).thenReturn(message);

        alertService.sendTodaysEvents();

        verify(nationwideEvent, times(1)).message();
        verify(twilioService, times(1)).sendTwilioText(anyString(), anyString());
    }
    @Test
    void testSendTodaysEvents_NoEventToday() {
        String todaysDate = "2024-05-21";
        String eventDate = "2024-05-22";
        when(dateUtil.getTodaysDate()).thenReturn(todaysDate);
        when(nationwideArenaEvents.getUpcomingEvent()).thenReturn(nationwideEvent);
        when(nationwideEvent.getDate()).thenReturn(eventDate);
        when(twilioService.sendTwilioText(anyString(), anyString())).thenReturn(message);

        alertService.sendTodaysEvents();

        verify(nationwideEvent, times(0)).message();
        verify(twilioService, times(1)).sendTwilioText(anyString(), anyString());
    }

    @Test
    void testSendYesterdaysEvents_NoEventToday() {
        String todaysDate = "2024-05-21";
        String eventDate = "2024-05-20";
        when(dateUtil.getTodaysDate()).thenReturn(todaysDate);
        when(nationwideArenaEvents.getUpcomingEvent()).thenReturn(nationwideEvent);
        when(nationwideEvent.getDate()).thenReturn(eventDate);
        when(twilioService.sendTwilioText(anyString(), anyString())).thenReturn(message);

        alertService.sendTodaysEvents();

        verify(nationwideEvent, times(0)).message();
        verify(twilioService, times(1)).sendTwilioText(anyString(), anyString());
    }
}