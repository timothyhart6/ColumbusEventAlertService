package com.ColumbusEventAlertService.services;

import com.ColumbusEventAlertService.services.columbusEvents.NationwideArenaService;
import com.ColumbusEventAlertService.models.NationwideEvent;
import com.ColumbusEventAlertService.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlertService {
    private DateUtil dateUtil;
    private NationwideArenaService nationwideArenaService;
    private NationwideEvent nationwideEvent;
    private TwilioService twilioService;


    public AlertService(DateUtil dateUtil, NationwideArenaService nationwideArenaService, NationwideEvent nationwideEvent, TwilioService twilioService) {
        this.dateUtil = dateUtil;
        this.nationwideArenaService = nationwideArenaService;
        this.nationwideEvent = nationwideEvent;
        this.twilioService = twilioService;
    }


    public String sendTodaysEvents() {
        String todaysDate = dateUtil.getTodaysDate();
        nationwideEvent = nationwideArenaService.getUpcomingEvent();
        String eventDate = nationwideEvent.getDate();
        String textMessage = (eventDate.equals(todaysDate)) ? nationwideEvent.message() : "No Events today!";
        String response = twilioService.sendTwilioText(System.getenv("TESTING_PHONE_NUMBER"),System.getenv("TWILIO_PHONE_NUMBER"), textMessage);

        return response;
    }
}
