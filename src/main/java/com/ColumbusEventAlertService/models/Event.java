package com.ColumbusEventAlertService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {
    private String name;
    private String date;
    private String time;

    public String textMessage() {
        return "";
    }
}