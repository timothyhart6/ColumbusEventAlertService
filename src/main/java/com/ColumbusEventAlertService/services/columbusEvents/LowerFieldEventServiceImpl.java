package com.ColumbusEventAlertService.services.columbusEvents;

import com.ColumbusEventAlertService.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import java.time.Year;

@Slf4j
public class LowerFieldEventServiceImpl extends EventServiceImpl {

    public LowerFieldEventServiceImpl(String url, JsoupService jsoupService, DateUtil dateUtil, String locationName) {
        super(url, jsoupService, dateUtil, locationName);
    }

    @Override
    protected String getEventName(Document doc) {
       return doc.getElementsByClass("awb-custom-text-color awb-custom-text-hover-color").get(0).childNode(0).toString().trim();
    }

    @Override
    protected String getTime(Document doc) {
    return "Time is not available";
    }
    @Override
    protected String getDateYear(Document doc) {
        return Year.now().toString();
    }
    @Override
    protected String getDateMonth(Document doc, DateUtil dateUtil) {
        String date = doc.getElementsByClass("tribe-event-date-start").get(0).childNode(0).toString().trim();
        String[] monthAndDay = date.split(" ");
        String monthName = monthAndDay[0];
        return dateUtil.convertMonthNameToNumber(monthName);
    }
    @Override
    protected String getDateDay(Document doc, DateUtil dateUtil) {
        String date = doc.getElementsByClass("tribe-event-date-start").get(0).childNode(0).toString().trim();
        String[] monthAndDay = date.split(" ");
        return dateUtil.formatDay(monthAndDay[1]);
    }
}