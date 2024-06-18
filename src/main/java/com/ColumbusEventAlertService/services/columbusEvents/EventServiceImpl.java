package com.ColumbusEventAlertService.services.columbusEvents;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;

import java.io.IOException;

@Slf4j
public abstract class EventServiceImpl implements EventService{
    private final String url;
    private final JsoupService jsoupService;
    private final DateUtil dateUtil;
    private final String locationName;

    public EventServiceImpl(String url, JsoupService jsoupService, DateUtil dateUtil, String locationName) {
        this.url = url;
        this.jsoupService = jsoupService;
        this.dateUtil = dateUtil;
        this.locationName = locationName;
    }

    @Override
    public Event getUpcomingEvent() throws IllegalArgumentException {
        Event event = new Event();
        event.setLocationName(locationName);
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
        String eventName = "";
        String year = "";
        String dateMonth = "";
        String dateDay = "";
        String time = "";

        try {
            eventName = getEventName(doc);
        } catch (IndexOutOfBoundsException e) {
            eventName = "Could not find event name";
            log.info("Event name node has changed location: " + e.getMessage());
        }
        try {
            year = getDateYear(doc);
        } catch (IndexOutOfBoundsException e) {
            year = "Could not find event year";
            log.info("Event year node has changed location: " + e.getMessage());
        }
        try {
            dateMonth = getDateMonth(doc, dateUtil);
        } catch (IndexOutOfBoundsException e) {
            dateMonth = "Could not find event month";
            log.info("Event month node has changed location: " + e.getMessage());
        }
        try {
            dateDay = getDateDay(doc, dateUtil);
        } catch (IndexOutOfBoundsException e) {
            dateDay = "Could not find event day";
            log.info("Event day node has changed location: " + e.getMessage());
        }
        try {
            time = getTime(doc);
        } catch (IndexOutOfBoundsException e) {
            time = "Could not find event time";
            log.info("Event time node has changed location: " + e.getMessage());
        }

        String formattedDate = dateMonth + "-" + dateDay + "-" + year;
        event.setEventName(eventName);
        event.setDate(formattedDate);
        event.setTime(time);
    }

    protected abstract String getEventName(Document doc);

    protected abstract String getTime(Document doc);

    protected abstract String getDateYear(Document doc);

    protected abstract String getDateMonth(Document doc, DateUtil dateUtil);

    protected abstract String getDateDay(Document doc, DateUtil dateUtil);
}