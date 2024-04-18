package com.ColumbusEventAlertService.services;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AlertService {

    public void sendTodaysEvents(Event event, TwilioService twilioService) {
        String todaysDate = new DateUtil().getTodaysDate();
        try {
            if (event.getDate().equals(todaysDate)) {
                twilioService.sendTwilioText(System.getenv("TESTING_PHONE_NUMBER"), event.textMessage());
            }
        } catch (NullPointerException e) {
            log.info("Event date was null");
        }
    }
}
