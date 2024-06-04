package com.ColumbusEventAlertService.tasks;

import com.ColumbusEventAlertService.services.columbusEvents.JsoupServiceImpl;
import com.ColumbusEventAlertService.services.AlertService;
import com.ColumbusEventAlertService.services.TwilioMessageSender;
import com.ColumbusEventAlertService.services.TwilioService;
import com.ColumbusEventAlertService.services.columbusEvents.NationwideEventServiceImpl;
import com.ColumbusEventAlertService.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScheduleApplicationTask {
    String nationwideUrl = "https://www.nationwidearena.com/events";
    TwilioService twilioService = new TwilioService(new TwilioMessageSender());
    NationwideEventServiceImpl eventServiceImpl = new NationwideEventServiceImpl(nationwideUrl, new JsoupServiceImpl(), new DateUtil(), "Nationwide Arena");
    DateUtil dateUtil = new DateUtil();
    AlertService alertService = new AlertService(dateUtil, eventServiceImpl, twilioService);

    public String sendTextAlerts() {
        log.info("Text Message is sending...");
        String response = alertService.sendTodaysEvents();
        log.info("Text Message has been sent");

        return response;
    }
}
