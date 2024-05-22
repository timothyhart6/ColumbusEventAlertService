package com.ColumbusEventAlertService.models;

public class NationwideEvent extends Event {

    public String message() {
        return "NATIONWIDE EVENT " + getDate() + ": " + getName() + " starting at " + getTime() + ".";
    }
}