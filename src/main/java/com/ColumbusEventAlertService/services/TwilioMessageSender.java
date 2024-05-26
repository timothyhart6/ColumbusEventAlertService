package com.ColumbusEventAlertService.services;

import com.twilio.rest.api.v2010.account.Message;

public class TwilioMessageSender {
    public Message sendMessage(String toPhoneNumber, String fromPhoneNumber, String messageText) {
        return Message.creator(
                        new com.twilio.type.PhoneNumber(toPhoneNumber),
                        new com.twilio.type.PhoneNumber(fromPhoneNumber),
                        messageText)
                .create();
    }
}
