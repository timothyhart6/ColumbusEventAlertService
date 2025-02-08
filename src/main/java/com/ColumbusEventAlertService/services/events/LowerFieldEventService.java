package com.ColumbusEventAlertService.services.events;

import com.ColumbusEventAlertService.services.JsoupService;
import com.ColumbusEventAlertService.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Year;

@Slf4j
@Service
public class LowerFieldEventService extends EventService {

    public LowerFieldEventService(@Value("${venue-name.lower}") String venueName,
                                  @Value("${url.lower}") String venueUrl,
                                  JsoupService jsoupService,
                                  DateUtil dateUtil)
    {
        super(jsoupService, dateUtil);
        super.venueName = venueName;
        super.venueUrl = venueUrl;
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
        return super.dateUtil.convertMonthNameToNumber(monthName);
    }
    @Override
    protected String getDateDay(Document doc) {
        String date = doc.getElementsByClass("tribe-event-date-start").get(0).childNode(0).toString().trim();
        String[] monthAndDay = date.split(" ");
        return super.dateUtil.formatDay(monthAndDay[1]);
    }

    @Override
    protected boolean isBadTraffic() { return true; }

    @Override
    protected boolean isDesiredEvent() { return false; }

}