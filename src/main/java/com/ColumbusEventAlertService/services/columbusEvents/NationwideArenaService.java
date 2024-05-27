package com.ColumbusEventAlertService.services.columbusEvents;

import com.ColumbusEventAlertService.models.NationwideEvent;
import com.ColumbusEventAlertService.utils.DateUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;

public class NationwideArenaService {
    private final String url;
    private final JsoupService jsoupService;
    private final DateUtil dateUtil;

    public NationwideArenaService(String url, JsoupService jsoupService, DateUtil dateUtil) {
        this.url = url;
        this.jsoupService = jsoupService;
        this.dateUtil = dateUtil;
    }

    public NationwideEvent getUpcomingEvent() throws IllegalArgumentException {
        NationwideEvent event = new NationwideEvent();
        try {
            Document doc = jsoupService.getDocument(jsoupService.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36"));

            parseEventDetails(doc, event);

        } catch (IllegalArgumentException | IOException e) {
            throw new IllegalArgumentException("Invalid URL: " + url);
        }
        return event;
    }

    private void parseEventDetails(Document doc, NationwideEvent event) {
        Elements eventInfo = doc.select("#list").get(0).getElementsByClass("info clearfix");
        String monthName = eventInfo.get(0).getElementsByClass("m-date__month").get(0).childNode(0).toString().trim();
        String day = eventInfo.get(0).getElementsByClass("m-date__day").get(0).childNode(0).toString().replaceAll("\\D", "");
        String year = eventInfo.get(0).getElementsByClass("m-date__year").get(0).childNode(0).toString().replaceAll("\\D", "");
//        String weekday = eventInfo.get(0).getElementsByClass("m-date__weekday").get(0).childNode(0).toString().replace("|", "").trim();
        String time = eventInfo.get(3).getElementsByClass("start").get(0).childNode(0).toString().trim();
        String eventName = eventInfo.get(0).getElementsByClass("title title-withTagline ").get(0).childNode(1).childNode(0).toString().trim();

        String monthNumber = dateUtil.convertMonthNameToNumber(monthName);
        String formattedDate = monthNumber + "-" + day + "-" + year;

        event.setName(eventName);
        event.setDate(formattedDate);
        event.setTime(time);
    }
}