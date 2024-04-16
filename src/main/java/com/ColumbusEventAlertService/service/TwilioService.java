package com.ColumbusEventAlertService.service;

import com.ColumbusEventAlertService.secrets.TwilioSecrets;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class TwilioService {

    public Message sendNationwideEvent(String phoneNumber, String messageText) {
        Twilio.init(TwilioSecrets.SID.getValue(), TwilioSecrets.AUTH_TOKEN.getValue());

        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(phoneNumber),
                        new com.twilio.type.PhoneNumber(TwilioSecrets.TWILIO_PHONE_NUMBER.getValue()),
                        messageText)
                .create();

        return message;
    }
}
