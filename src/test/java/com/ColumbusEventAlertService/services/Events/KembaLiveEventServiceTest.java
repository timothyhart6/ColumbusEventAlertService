package com.ColumbusEventAlertService.services.Events;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.JsoupService;
import com.ColumbusEventAlertService.services.events.KembaLiveEventService;
import com.ColumbusEventAlertService.utils.DateUtil;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KembaLiveEventServiceTest {
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
        KembaLiveEventService subject = new KembaLiveEventService("Venue", "url", jsoupService, dateUtil);

        when(connection.userAgent(anyString())).thenReturn(connection);
        when(jsoupService.connect(anyString())).thenReturn(connection);
        when(jsoupService.getDocument(connection)).thenReturn(document);

        //DeepStub all the fields
        when(jsoupService.getDocument(connection.userAgent(anyString())).getElementsByClass("info").get(0).select("h2").get(0).childNode(0).toString()).thenReturn("Kemba Event");
        when(jsoupService.getDocument(connection.userAgent(anyString())).getElementsByClass("date").get(0).childNode(3).childNode(1).childNode(0).toString()).thenReturn("January 02");
        when(dateUtil.convertMonthNameToNumber(anyString())).thenReturn("01");
        when(dateUtil.formatDay(anyString())).thenReturn("02");
        when(jsoupService.getDocument(connection.userAgent(anyString())).getElementsByClass("doors-time").get(1).childNode(0).toString()).thenReturn("7:00 PM");

        Event event = subject.getNextEvent();

        assertEquals("Kemba Event", event.getEventName());
        assertTrue(event.getDate().startsWith("01-02"));
        assertEquals("7:00 PM", event.getTime());
    }
}