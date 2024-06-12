package com.ColumbusEventAlertService.services.columbusEvents;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.time.Year;
import java.util.Date;

@Slf4j
public class KembaLiveEventServiceImpl extends EventServiceImpl{
    public KembaLiveEventServiceImpl(String url, JsoupService jsoupService, DateUtil dateUtil, String locationName) {
        super(url, jsoupService, dateUtil, locationName);
    }

    @Override
    public void parseEventDetails(Document doc, Event event, DateUtil dateUtil) {
        try {
            Element eventInfo = doc.getElementsByClass("events-list").get(0).getElementsByClass("inner").get(0).getElementsByClass("info").get(0);

            String eventName = eventInfo.childNode(1).childNode(0).toString();
            String date = eventInfo.getElementsByClass("date").get(0).childNode(3).childNode(1).childNode(0).toString();
            String time = eventInfo.getElementsByClass("doors-time").get(1).childNode(0).toString();
            String[] monthAndDay = date.split(" ");
            String day = dateUtil.formatDay(monthAndDay[1]);
            String monthName = monthAndDay[0];
            String monthNumber = dateUtil.convertMonthNameToNumber(monthName);
            String year = Year.now().toString();
            String formattedDate = monthNumber + "-" + day + "-" + year;

            event.setEventName(eventName);
            event.setDate(formattedDate);
            event.setTime(time);
        } catch (IndexOutOfBoundsException e) {
            log.info("Issue mapping Kemba Event");
        }
    }
}
