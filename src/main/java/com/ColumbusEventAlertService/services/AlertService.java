package com.ColumbusEventAlertService.services;

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
