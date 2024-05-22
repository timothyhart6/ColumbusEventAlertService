package com.ColumbusEventAlertService.services;

import com.ColumbusEventAlertService.columbusEvents.NationwideArenaEvents;
import com.ColumbusEventAlertService.models.NationwideEvent;
import com.ColumbusEventAlertService.utils.DateUtil;
import com.twilio.rest.api.v2010.account.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlertService {
    private DateUtil dateUtil;
    private NationwideArenaEvents nationwideArenaEvents;
    private NationwideEvent nationwideEvent;
    private TwilioService twilioService;


    public AlertService(DateUtil dateUtil, NationwideArenaEvents nationwideArenaEvents, NationwideEvent nationwideEvent, TwilioService twilioService) {
        this.dateUtil = dateUtil;
        this.nationwideArenaEvents = nationwideArenaEvents;
        this.nationwideEvent = nationwideEvent;
        this.twilioService = twilioService;
    }


    public String sendTodaysEvents() {
        String todaysDate = dateUtil.getTodaysDate();
        nationwideEvent = nationwideArenaEvents.getUpcomingEvent();
        String eventDate = nationwideEvent.getDate();
        String textMessage = (eventDate.equals(todaysDate)) ? nationwideEvent.message() : "No Events today!";
        Message responseMessage = twilioService.sendTwilioText(System.getenv("TESTING_PHONE_NUMBER"), textMessage);
        log.info("Message sent:" + responseMessage.getBody());

        return responseMessage.getBody();
    }
}
