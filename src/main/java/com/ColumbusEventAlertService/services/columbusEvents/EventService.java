package com.ColumbusEventAlertService.services.columbusEvents;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.utils.DateUtil;
import org.jsoup.nodes.Document;

public interface EventService {
    Event getUpcomingEvent() throws IllegalArgumentException;

    void parseEventDetails(Document doc, Event event, DateUtil dateUtil);
}