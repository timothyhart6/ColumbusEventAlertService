package com.ColumbusEventAlertService.services;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.utils.DateUtil;

public class AlertService {

    public void sendTodaysEvents(Event event, TwilioService twilioService) {
        String todaysDate = new DateUtil().getTodaysDate();

        if(event.getDate().equals(todaysDate)) {
            twilioService.sendTwilioText(System.getenv("TESTING_PHONE_NUMBER"), event.textMessage());
        }
    }

    public String sendEvent() {
        TwilioService twilioService = new TwilioService();
        twilioService.sendTwilioText(System.getenv("TESTING_PHONE_NUMBER"), "Hello from Lambda Test");
    return "Success!";
    }

}
