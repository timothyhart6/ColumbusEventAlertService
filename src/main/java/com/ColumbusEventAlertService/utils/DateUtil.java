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
        return switch (monthName.toLowerCase()) {
            case "jan" -> "01";
            case "feb" -> "02";
            case "mar" -> "03";
            case "apr" -> "04";
            case "may" -> "05";
            case "june" -> "06";
            case "july" -> "07";
            case "aug" -> "08";
            case "sep" -> "09";
            case "oct" -> "10";
            case "nov" -> "11";
            case "dec" -> "12";
            default -> "Could not map month: " + monthName;
        };
    }

    public String formatDay(String day) {
        return (day.length() == 1) ? "0" + day : day;
    }
}