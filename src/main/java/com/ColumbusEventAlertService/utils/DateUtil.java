package com.ColumbusEventAlertService.utils;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public String getTodaysDate() {
        LocalDate todaysDate = LocalDate.now();
        return formatDate(todaysDate);
    }

    public String formatDate(LocalDate date) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return date.format(dateFormat);
    }

    public String formatGoogleDate(String date) {
        String month;
        String day;
        String year;

        String[] dateSections = date.split(" ");
        month = convertMonthNameToNumber(dateSections[1]);
        day = (dateSections[2].length() == 1) ? "0" + dateSections[2] : dateSections[2];
        year = Year.now().toString();

        String formattedDate = month + "-" + day + "-" + year;

        return formattedDate;
    }

    public String convertMonthNameToNumber(String monthName) {
        switch(monthName.toLowerCase()) {
            case "jan":
                return "01";
            case "feb" :
                return "02";
            case "mar":
                return "03";
            case "apr":
                return "04";
            case "may":
                return "05";
            case "june":
                return "06";
            case "july;":
                return "07";
            case "aug":
                return "08";
            case "sep":
                return "09";
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