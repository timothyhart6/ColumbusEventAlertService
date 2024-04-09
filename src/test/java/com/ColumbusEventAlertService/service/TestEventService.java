package com.ColumbusEventAlertService.service;

import com.twilio.rest.api.v2010.account.Message;
import org.junit.Assert;
import org.junit.Test;

public class TestEventService {

    @Test
    public void nationwideEventSendsSuccessfully() {
        EventService eventService = new EventService();
        String phoneNumber = "+18452718721";
        String messageText = "Hello from the Unit Test";
       Message message = eventService.sendNationwideEvent(phoneNumber, messageText);
        Assert.assertNotNull(message);
        Assert.assertNull(message.getErrorMessage());
        Assert.assertNull(message.getErrorCode());
    }
}
