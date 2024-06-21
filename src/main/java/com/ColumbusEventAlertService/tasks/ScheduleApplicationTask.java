package com.ColumbusEventAlertService.tasks;

import com.ColumbusEventAlertService.services.TextMessageService;
import com.ColumbusEventAlertService.services.TwilioMessageSender;
import com.ColumbusEventAlertService.services.TwilioService;
import com.ColumbusEventAlertService.utils.EventsUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScheduleApplicationTask {
    TwilioService twilioService = new TwilioService(new TwilioMessageSender());
    EventsUtil eventsUtil = new EventsUtil();
    TextMessageService textMessageService = new TextMessageService(twilioService, eventsUtil);

    public String sendTextAlerts() {
        log.info("Text Message is sending...");
        String response = textMessageService.sendTodaysEvents();
        log.info("Text Message has been sent");

        return response;
    }
}
