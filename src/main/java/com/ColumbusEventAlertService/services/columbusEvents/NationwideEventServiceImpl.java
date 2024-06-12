package com.ColumbusEventAlertService.services.columbusEvents;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@Slf4j
public class NationwideEventServiceImpl extends EventServiceImpl {

    public NationwideEventServiceImpl(String url, JsoupService jsoupService, DateUtil dateUtil, String locationName) {
        super(url, jsoupService, dateUtil, locationName);
    }

    @Override
    public void parseEventDetails(Document doc, Event event, DateUtil dateUtil) {
        try {
            Elements eventInfo = doc.select("#list").get(0).getElementsByClass("info clearfix");
            String monthName = eventInfo.get(0).getElementsByClass("m-date__month").get(0).childNode(0).toString().trim();
            String day = eventInfo.get(0).getElementsByClass("m-date__day").get(0).childNode(0).toString().replaceAll("\\D", "");
            String year = eventInfo.get(0).getElementsByClass("m-date__year").get(0).childNode(0).toString().replaceAll("\\D", "");
//        String weekday = eventInfo.get(0).getElementsByClass("m-date__weekday").get(0).childNode(0).toString().replace("|", "").trim();
            String time = eventInfo.get(2).getElementsByClass("start").get(0).childNode(0).toString().trim();

            String eventName = eventInfo.get(0).getElementsByClass("title title-withTagline ").get(0).childNode(1).childNode(0).toString().trim();
            day = dateUtil.formatDay(day);
            String monthNumber = dateUtil.convertMonthNameToNumber(monthName);
            String formattedDate = monthNumber + "-" + day + "-" + year;

            event.setEventName(eventName);
            event.setDate(formattedDate);
            event.setTime(time);
        } catch (IndexOutOfBoundsException e) {
            log.info("Issue mapping Nationwide Event");
        }
    }
}
