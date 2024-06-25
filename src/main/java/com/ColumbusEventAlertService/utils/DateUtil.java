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
            case "jan", "january" -> "01";
            case "feb", "february" -> "02";
            case "mar", "march" -> "03";
            case "apr", "april" -> "04";
            case "may" -> "05";
            case "jun", "june" -> "06";
            case "jul", "july" -> "07";
            case "aug", "august" -> "08";
            case "sep", "september" -> "09";
            case "oct", "october" -> "10";
            case "nov", "november" -> "11";
            case "dec", "december" -> "12";
            default -> "Could not map month: " + monthName;
        };
    }

    public String formatDay(String day) {
        return (day.length() == 1) ? "0" + day : day;
    }
}