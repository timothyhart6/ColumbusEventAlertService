package com.ColumbusEventAlertService.services;

import com.ColumbusEventAlertService.GatherEvents;
import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.smsProviders.TwilioService;
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
    private GatherEvents gatherEvents;

    ArrayList<Event> events;

    @BeforeEach
    public void setup() {
        events = new ArrayList<>();
    }

    @Test
    public void testSendTodaysEvents() {

        textMessageService.sendTodaysEvents();
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);

        verify(twilioService).sendTextMessage(messageCaptor.capture());
    }

    @Test
    void testSendTodaysEvents_NoEventToday() {
        String expectedMessage = "No Events today!";
        when(gatherEvents.getTodaysEvents()).thenReturn(events);

        textMessageService.sendTodaysEvents();
        verify(twilioService).sendTextMessage(eq(expectedMessage));
    }
}