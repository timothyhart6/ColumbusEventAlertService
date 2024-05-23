package com.ColumbusEventAlertService.columbusEvents;

import com.ColumbusEventAlertService.models.NationwideEvent;
import com.ColumbusEventAlertService.utils.DateUtil;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Node;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class NationwideArenaEvents {
    @Setter
    private String nationwideEventsUrl = "https://www.nationwidearena.com/events";

    public NationwideEvent getUpcomingEvent() throws IllegalArgumentException {
        NationwideEvent event = new NationwideEvent();
        try {
            Document doc = Jsoup.connect(nationwideEventsUrl).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36").get();

            Elements eventInfo = doc.select("#list").get(0).getElementsByClass("info clearfix");
            String monthName = eventInfo.get(0).getElementsByClass("m-date__month").get(0).childNode(0).toString().trim();
            String day = eventInfo.get(0).getElementsByClass("m-date__day").get(0).childNode(0).toString().trim();
            String year = eventInfo.get(0).getElementsByClass("m-date__year").get(0).childNode(0).toString().trim();
            String weekday = eventInfo.get(0).getElementsByClass("m-date__weekday").get(0).childNode(0).toString().replace("|", "").trim();
            String time = eventInfo.get(3).getElementsByClass("start").get(0).childNode(0).toString().trim();
            String eventName = eventInfo.get(0).getElementsByClass("title title-withTagline ").get(0).childNode(1).childNode(0).toString().trim();

            String monthNumber = new DateUtil().convertMonthNameToNumber(monthName);
            String formattedDate = monthNumber + "-" + day.replaceAll("\\D", ""); + "-" + year.replaceAll("\\D", "");

            event.setName(eventName);
            event.setDate(formattedDate);
            event.setTime(time);

        } catch (IllegalArgumentException | IOException e) {
            throw new IllegalArgumentException("Invalid URL: " + nationwideEventsUrl);
        }
        return event;
    }
}