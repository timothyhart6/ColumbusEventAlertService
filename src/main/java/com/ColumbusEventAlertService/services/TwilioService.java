package com.ColumbusEventAlertService.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TwilioService {

    TwilioMessageSender twilioMessageSender;

    public TwilioService(TwilioMessageSender twilioMessageSender) {
        this.twilioMessageSender = twilioMessageSender;
    }

    public String sendTwilioText(String toPhoneNumber, String fromPhoneNumber, String messageText) {

        Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));

        Message message = twilioMessageSender.sendMessage(
                toPhoneNumber,
                fromPhoneNumber,
                messageText);

        if (message == null || message.getErrorMessage() != null && !message.getErrorMessage().isEmpty()) {
            log.info("Message failed to create. Error message: " + (message == null ? "null" : message.getErrorMessage()));
            return message == null ? "null" : message.getErrorMessage();
        } else {
            log.info("Message sent successfully. Message sent: " + message.getBody());
            return message.getBody();
        }
    }
}