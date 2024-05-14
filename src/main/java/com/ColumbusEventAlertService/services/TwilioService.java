package com.ColumbusEventAlertService.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.stereotype.Component;

@Component
public class TwilioService {

    public Message sendTwilioText(String phoneNumber, String messageText) {
        Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));

        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(phoneNumber),
                        new com.twilio.type.PhoneNumber(System.getenv("TWILIO_PHONE_NUMBER")),
                        messageText)
                .create();

        return message;
    }
}
