package com.ColumbusEventAlertService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {
    private String locationName;
    private String eventName;
    private String date;
    private String time;

    public Event(String locationName) {
        this.locationName = locationName;
    }
}