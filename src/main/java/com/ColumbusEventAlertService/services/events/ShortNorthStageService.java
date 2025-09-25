/*
package com.ColumbusEventAlertService.services.events;

import com.ColumbusEventAlertService.services.JsoupService;
import com.ColumbusEventAlertService.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShortNorthStageService extends EventService {

    public ShortNorthStageService(JsoupService jsoupService, DateUtil dateUtil) {
        super(jsoupService, dateUtil);
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
        return "";
    }

    @Override
    protected String getDateDay(Document doc) {
        return "";
    }
}
*/
