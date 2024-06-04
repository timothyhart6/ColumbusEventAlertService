package com.ColumbusEventAlertService.services.columbusEvents;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.utils.DateUtil;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class EventServiceImpl implements EventService{
    private final String url;
    private final JsoupService jsoupService;
    private final DateUtil dateUtil;

    public EventServiceImpl(String url, JsoupService jsoupService, DateUtil dateUtil) {
        this.url = url;
        this.jsoupService = jsoupService;
        this.dateUtil = dateUtil;
    }

    @Override
    public Event getUpcomingEvent() throws IllegalArgumentException {
        Event event = new Event();
        try {
            Document doc = jsoupService.getDocument(jsoupService.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36"));

            parseEventDetails(doc, event, dateUtil);

        } catch (IllegalArgumentException | IOException e) {
            throw new IllegalArgumentException("Invalid URL: " + url);
        }
        return event;
    }

    public void parseEventDetails(Document doc, Event event, DateUtil dateUtil) {

    }
}