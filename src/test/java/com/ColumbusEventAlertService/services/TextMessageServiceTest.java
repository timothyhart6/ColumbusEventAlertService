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
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TextMessageServiceTest {
    @InjectMocks
    private TextMessageService textMessageService;
    @Mock
    private TwilioService twilioService;
    @Mock
    private EventsUtil eventsUtil;

    ArrayList<Event> events;

    @BeforeEach
    public void setup() {
        events = new ArrayList<>();
    }

    @Test
    public void testSendTodaysEvents() {

        textMessageService.sendTodaysEvents();

        ArgumentCaptor<String> fromCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> toCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);

        verify(twilioService).sendTwilioText(fromCaptor.capture(), toCaptor.capture(), messageCaptor.capture());
    }

    @Test
    void testSendTodaysEvents_NoEventToday() {
        String expectedMessage = "No Events today!";
        when(eventsUtil.getTodaysEvents()).thenReturn(events);

        textMessageService.sendTodaysEvents();
        verify(twilioService).sendTwilioText(anyString(), anyString(), eq(expectedMessage));
    }
}