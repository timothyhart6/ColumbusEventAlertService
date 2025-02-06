package com.ColumbusEventAlertService.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Event {
    private String locationName;
    private String eventName;
    private String date;
    private String time;
    private boolean badTraffic;
    private boolean desiredEvent;

    public Event(String locationName) {
        this.locationName = locationName;
    }

    public Event(String locationName, String eventName, String date, String time, boolean badTraffic, boolean desiredEvent) {
        this.locationName = locationName;
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.badTraffic = badTraffic;
        this.desiredEvent = desiredEvent;
    }
}