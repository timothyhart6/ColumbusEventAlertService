package com.ColumbusEventAlertService.services.events;

import com.ColumbusEventAlertService.services.JsoupService;
import com.ColumbusEventAlertService.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class ShortNorthStageService extends EventService {

    LocalDate today;
    public ShortNorthStageService(
            @Value("${venue-name.sn-stage}") String venueName,
            @Value("${url.sn-stage}") String venueUrlTemplate,
            JsoupService jsoupService,
            DateUtil dateUtil) {
        super(jsoupService, dateUtil);
        super.venueName = venueName;
        this.today = dateUtil.getCurrentDate();
        super.venueUrl = buildUrl(venueUrlTemplate, today, today);
        super.isBadTraffic = true;
        super.isDesiredEvent = false;
    }

    @Override
    protected String getEventName(Document doc) {
        return doc.getElementsByClass("text-xl font-bold mt-0 mb-0").text();
    }

    @Override
    protected String getTime(Document doc) {
        return "";
    }

    @Override
    protected String getDateMonth(Document doc) {
        return dateUtil.getMonth();
    }

    @Override
    protected String getDateDay(Document doc) {
        return dateUtil.getDay();
    }
}
