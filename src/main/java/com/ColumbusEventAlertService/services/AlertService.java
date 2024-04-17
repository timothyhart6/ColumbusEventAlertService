package com.ColumbusEventAlertService.services;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.utils.DateUtil;
import static com.ColumbusEventAlertService.secrets.TwilioSecrets.TESTING_PHONE_NUMBER;

public class AlertService {

    public void sendTodaysEvents(Event event, TwilioService twilioService) {
        String todaysDate = new DateUtil().getTodaysDate();

        if(event.getDate().equals(todaysDate)) {
            twilioService.sendTwilioText(TESTING_PHONE_NUMBER.getValue(), event.textMessage());
        }
    }
}
