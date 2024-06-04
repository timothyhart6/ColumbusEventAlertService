package com.ColumbusEventAlertService.services.columbusEvents;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.utils.DateUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class NationwideEventServiceImpl extends EventServiceImpl {

    public NationwideEventServiceImpl(String url, JsoupService jsoupService, DateUtil dateUtil, String locationName) {
        super(url, jsoupService, dateUtil, locationName);
    }

    @Override
   public void parseEventDetails(Document doc, Event event, DateUtil dateUtil) {
        Elements eventInfo = doc.select("#list").get(0).getElementsByClass("info clearfix");
        String monthName = eventInfo.get(0).getElementsByClass("m-date__month").get(0).childNode(0).toString().trim();
        String day = eventInfo.get(0).getElementsByClass("m-date__day").get(0).childNode(0).toString().replaceAll("\\D", "");
        String year = eventInfo.get(0).getElementsByClass("m-date__year").get(0).childNode(0).toString().replaceAll("\\D", "");
//        String weekday = eventInfo.get(0).getElementsByClass("m-date__weekday").get(0).childNode(0).toString().replace("|", "").trim();
        String time = eventInfo.get(3).getElementsByClass("start").get(0).childNode(0).toString().trim();
        String eventName = eventInfo.get(0).getElementsByClass("title title-withTagline ").get(0).childNode(1).childNode(0).toString().trim();

       String monthNumber = dateUtil.convertMonthNameToNumber(monthName);
        String formattedDate = monthNumber + "-" + day + "-" + year;

        event.setEventName(eventName);
        event.setDate(formattedDate);
        event.setTime(time);
    }
}
