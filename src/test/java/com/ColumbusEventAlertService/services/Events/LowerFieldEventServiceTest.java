package com.ColumbusEventAlertService.services.Events;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.JsoupService;
import com.ColumbusEventAlertService.services.events.LowerFieldEventService;
import com.ColumbusEventAlertService.utils.DateUtil;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class LowerFieldEventServiceTest {
    @Mock
    private DateUtil dateUtil;
    @Mock
    private JsoupService jsoupService;
    @Mock
    private Connection connection;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    Document document;

    @Test
    public void testGetUpcomingEvent_success() throws Exception {
        LowerFieldEventService subject = new LowerFieldEventService("Venue", "url", jsoupService, dateUtil);

        when(connection.userAgent(anyString())).thenReturn(connection);
        when(jsoupService.connect(anyString())).thenReturn(connection);
        when(jsoupService.getDocument(connection)).thenReturn(document);

        //DeepStub all the fields
        when(jsoupService.getDocument(connection.userAgent(anyString())).getElementsByClass("awb-custom-text-color awb-custom-text-hover-color").get(0).childNode(0).toString().trim()).thenReturn("Lower.com Field Event");
        when(jsoupService.getDocument(connection.userAgent(anyString())).getElementsByClass("tribe-event-date-start").get(0).childNode(0).toString().trim()).thenReturn("Jul 22");
        when(dateUtil.convertMonthNameToNumber(anyString())).thenReturn("07");
        when(dateUtil.formatDay(anyString())).thenReturn("22");

        Event event = subject.getNextEvent();

        assertEquals("Lower.com Field Event", event.getEventName());
        assertEquals("Time is not available", event.getTime());
    }
}