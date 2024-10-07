package com.ColumbusEventAlertService.services.events;

import com.ColumbusEventAlertService.services.JsoupService;
import com.ColumbusEventAlertService.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;

import java.time.Year;

@Slf4j
public class NewportEventService extends EventService {
    public NewportEventService(JsoupService jsoupService, DateUtil dateUtil) {
        super(jsoupService, dateUtil);
    }

    @Override
    protected String getEventName(Document doc) {
        return doc.getElementsByClass("info").get(0).select("h2").get(0).childNode(0).toString();
    }

    @Override
    protected String getDateYear(Document doc) {
        return Year.now().toString();
    }

    @Override
    protected String getDateMonth(Document doc) {
        String date = doc.getElementsByClass("date").get(0).childNode(3).childNode(1).childNode(0).toString();
        String[] monthAndDay = date.split(" ");
        String monthName = monthAndDay[0];
        return getDateUtil().convertMonthNameToNumber(monthName);
    }

    @Override
    protected String getDateDay(Document doc) {
        String date = doc.getElementsByClass("date").get(0).childNode(3).childNode(1).childNode(0).toString();
        String[] monthAndDay = date.split(" ");
        return getDateUtil().formatDay(monthAndDay[1]);
    }

    @Override
    protected String getTime(Document doc) {
        return doc.getElementsByClass("featured-event").get(0).getElementsByClass("doors-time").get(0).childNode(0).toString();
    }
}