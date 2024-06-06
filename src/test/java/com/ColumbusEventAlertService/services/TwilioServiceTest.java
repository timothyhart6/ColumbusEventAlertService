package com.ColumbusEventAlertService.services;

import static org.mockito.Mockito.*;
import com.twilio.rest.api.v2010.account.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TwilioServiceTest {

    private TwilioMessageSender messageSenderMock;
    private TwilioService twilioService;

    String toPhoneNumber;
    String fromPhoneNumber;
    String messageText;
    Message mockMessage;

    @BeforeEach
    public void setUp() {
        messageSenderMock = mock(TwilioMessageSender.class);
        twilioService = new TwilioService(messageSenderMock);
        toPhoneNumber = "+1234567890";
        fromPhoneNumber = "+1234567890";
        messageText = "Test message";
        mockMessage = mock(Message.class);
    }

    @Test
    public void testSendTwilioText() {
        when(mockMessage.getBody()).thenReturn("Success!");
        when(messageSenderMock.sendMessage(anyString(), anyString(), anyString())).thenReturn(mockMessage);

        String result = twilioService.sendTwilioText(toPhoneNumber, fromPhoneNumber, messageText);

        verify(messageSenderMock).sendMessage(toPhoneNumber, fromPhoneNumber, messageText);
        assertEquals("Success!", result);
    }

    @Test
    public void testSendTwilioText_withError() {

        when(mockMessage.getErrorMessage()).thenReturn("Some error");

        when(messageSenderMock.sendMessage(anyString(), anyString(), anyString())).thenReturn(mockMessage);

        String result = twilioService.sendTwilioText(toPhoneNumber, fromPhoneNumber, messageText);

        verify(messageSenderMock).sendMessage(toPhoneNumber, fromPhoneNumber, messageText);
        assertEquals("Some error", result);
    }
}