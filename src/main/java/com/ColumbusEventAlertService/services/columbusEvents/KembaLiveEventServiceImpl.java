package com.ColumbusEventAlertService.services.columbusEvents;

import com.ColumbusEventAlertService.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;

import java.time.Year;

@Slf4j
public class KembaLiveEventServiceImpl extends EventServiceImpl{
    public KembaLiveEventServiceImpl(String url, JsoupService jsoupService, DateUtil dateUtil, String locationName) {
        super(url, jsoupService, dateUtil, locationName);
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
    protected String getDateMonth(Document doc, DateUtil dateUtil) {
       String date = doc.getElementsByClass("date").get(0).childNode(3).childNode(1).childNode(0).toString();
       String[] monthAndDay = date.split(" ");
       String monthName = monthAndDay[1];
       return dateUtil.convertMonthNameToNumber(monthName);
    }

    @Override
    protected String getDateDay(Document doc, DateUtil dateUtil) {
        String date = doc.getElementsByClass("date").get(0).childNode(3).childNode(1).childNode(0).toString();
        String[] monthAndDay = date.split(" ");
        return dateUtil.formatDay(monthAndDay[0]);
    }

    @Override
    protected String getTime(Document doc) {
        return doc.getElementsByClass("doors-time").get(0).childNode(0).toString();
    }
}
