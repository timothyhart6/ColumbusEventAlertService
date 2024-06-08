package com.ColumbusEventAlertService.services;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.utils.EventsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TextMessageServiceTest {
    @Mock
    private TwilioService twilioService;
    @Mock
    private EventsUtil eventsUtil;
    @InjectMocks
    private TextMessageService textMessageService;
    ArrayList<Event> events;
    Event event;

    @BeforeEach
    public void setup() {
        events = new ArrayList<>();
        event = new Event();

    }

    @Test
    public void testSendTodaysEvents() {
        event.setLocationName("Home");
        event.setEventName("TV");
        event.setTime("7:00PM");
        events.add(event);
        String expectedMessage = "TODAY'S EVENTS:\n Home:\n  TV at 7:00PM\n";
        when(eventsUtil.getTodaysEvents()).thenReturn(events);

        textMessageService.sendTodaysEvents();

        verify(twilioService).sendTwilioText(anyString(), anyString(), eq(expectedMessage));
    }

    @Test
    void testSendTodaysEvents_NoEventToday() {
        String expectedMessage = "No Events today!";
        when(eventsUtil.getTodaysEvents()).thenReturn(events);

        textMessageService.sendTodaysEvents();
        verify(twilioService).sendTwilioText(anyString(), anyString(), eq(expectedMessage));
    }
}