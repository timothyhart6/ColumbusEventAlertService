package com.ColumbusEventAlertService.utils;

import java.time.Year;

public class DateFormatter {
    public String formatGoogleDate(String date) {
        String month;
        String day;
        String year;

        String[] dateSections = date.split(" ");
        month = convertMonthNameToNumber(dateSections[1]);
        day = dateSections[2];
        year = Year.now().toString();

        String formattedDate = month + "-" + day + "-" + year;

        return formattedDate;
    }

    private String convertMonthNameToNumber(String monthName) {
        switch(monthName.toLowerCase()) {
            case "jan":
                return "1";
            case "feb" :
                return "2";
            case "mar":
                return "3";
            case "apr":
                return "4";
            case "may":
                return "5";
            case "jun":
                return "6";
            case "jul;":
                return "7";
            case "aug":
                return "8";
            case "sep":
                return "9";
            case "oct":
                return "10";
            case "nov":
                return "11";
            case "dec":
                return "12";
        }
        return "Could not map month: " + monthName;
    }
}