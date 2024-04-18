package com.ColumbusEventAlertService.tasks;

import com.ColumbusEventAlertService.columbusEvents.NationwideArenaEvents;
import com.ColumbusEventAlertService.services.AlertService;
import com.ColumbusEventAlertService.services.TwilioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduleApplicationTask {
    @Autowired
    AlertService alertService;

    @Autowired
    NationwideArenaEvents nationwideArenaEvents;

    @Autowired
    TwilioService twilioService;

    @Scheduled(cron = "0 0 9 * * *")
    public void sendTextAlerts() {
        alertService.sendTodaysEvents(nationwideArenaEvents.getUpcomingEvent(), twilioService);
        log.info("Text has been sent, if there was an event today");
    }
}
