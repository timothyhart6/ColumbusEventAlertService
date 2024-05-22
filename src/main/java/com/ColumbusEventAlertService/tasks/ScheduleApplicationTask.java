package com.ColumbusEventAlertService.tasks;

import com.ColumbusEventAlertService.columbusEvents.NationwideArenaEvents;
import com.ColumbusEventAlertService.models.NationwideEvent;
import com.ColumbusEventAlertService.services.AlertService;
import com.ColumbusEventAlertService.services.TwilioService;
import com.ColumbusEventAlertService.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScheduleApplicationTask {
    TwilioService twilioService = new TwilioService();
    NationwideArenaEvents nationwideArenaEvents = new NationwideArenaEvents();
    NationwideEvent nationwideEvent = new NationwideEvent();
    DateUtil dateUtil = new DateUtil();
    AlertService alertService = new AlertService(dateUtil, nationwideArenaEvents, nationwideEvent, twilioService);


    public String sendTextAlerts() {
        log.info("Text Message is sending...");
        String response = alertService.sendTodaysEvents();
        log.info("Text Message has been sent");

        return response;
    }
}
