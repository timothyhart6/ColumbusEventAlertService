package com.ColumbusEventAlertService.services;

import com.twilio.rest.api.v2010.account.Message;
import org.junit.Assert;
import org.junit.Test;

public class TestTwilioService {
    @Test
    public void twilioMessageSendsSuccessfully() {
        TwilioService twilioService = new TwilioService();
        String phoneNumber = System.getenv("TESTING_PHONE_NUMBER");
        String messageText = "Hello from the Unit Test";
        Message message = twilioService.sendTwilioText(phoneNumber, messageText);

        Assert.assertNotNull(message);
        Assert.assertNull(message.getErrorMessage());
        Assert.assertNull(message.getErrorCode());
    }
}