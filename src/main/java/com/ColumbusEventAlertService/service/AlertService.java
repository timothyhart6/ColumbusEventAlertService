package com.ColumbusEventAlertService.service;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.secrets.TwilioSecrets;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AlertService {

/*    public void sendTodaysEvents(Event event) {

        if(event.getDate() == getTodaysDate()) {
            sendNationwideEvent();
        }

    }*/


    private String getTodaysDate() {
        LocalDate todaysDateUnformatted = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String todaysDate = todaysDateUnformatted.format(dateFormat);
        return todaysDate;
    }
}
