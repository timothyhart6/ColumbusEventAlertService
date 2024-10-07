package com.ColumbusEventAlertService.services.events;

import com.ColumbusEventAlertService.services.JsoupService;
import com.ColumbusEventAlertService.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import java.time.Year;

@Slf4j
public class LowerFieldEventService extends EventService {

    public LowerFieldEventService(JsoupService jsoupService, DateUtil dateUtil) {
        super(jsoupService, dateUtil);
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
    protected String getDateMonth(Document doc) {
        String date = doc.getElementsByClass("tribe-event-date-start").get(0).childNode(0).toString().trim();
        String[] monthAndDay = date.split(" ");
        String monthName = monthAndDay[0];
        return getDateUtil().convertMonthNameToNumber(monthName);
    }
    @Override
    protected String getDateDay(Document doc) {
        String date = doc.getElementsByClass("tribe-event-date-start").get(0).childNode(0).toString().trim();
        String[] monthAndDay = date.split(" ");
        return getDateUtil().formatDay(monthAndDay[1]);
    }
}