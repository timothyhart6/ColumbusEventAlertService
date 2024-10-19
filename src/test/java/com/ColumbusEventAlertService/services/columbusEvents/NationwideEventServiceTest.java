package com.ColumbusEventAlertService.services.columbusEvents;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.JsoupService;
import com.ColumbusEventAlertService.services.events.NationwideEventService;
import com.ColumbusEventAlertService.utils.DateUtil;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NationwideEventServiceTest {
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
        NationwideEventService subject = new NationwideEventService("Venue", "Url", jsoupService, dateUtil);

        when(connection.userAgent(anyString())).thenReturn(connection);
        when(jsoupService.connect(anyString())).thenReturn(connection);
        when(jsoupService.getDocument(connection)).thenReturn(document);
        when(dateUtil.convertMonthNameToNumber("January")).thenReturn("01");
        when(dateUtil.formatDay("2")).thenReturn("02");

        //DeepStub all the fields
        when(jsoupService.getDocument(connection.userAgent(anyString())).getElementsByClass("m-date__month").get(0).childNode(0).toString().trim()).thenReturn("January");
        when(jsoupService.getDocument(connection.userAgent(anyString())).getElementsByClass("m-date__day").get(0).childNode(0).toString().replaceAll("\\D", "")).thenReturn("2");
        when(jsoupService.getDocument(connection.userAgent(anyString())).getElementsByClass("m-date__year").get(0).childNode(0).toString().replaceAll("\\D", "")).thenReturn("2024");
        when(jsoupService.getDocument(connection.userAgent(anyString())).getElementsByClass("time").get(0).getElementsByClass("start").get(0).childNode(0).toString()).thenReturn("7:00 PM");
        when(jsoupService.getDocument(connection.userAgent(anyString())).getElementsByClass("title title-withTagline ").get(0).childNode(1).childNode(0).toString().trim()).thenReturn("HockeyBall");

        Event event = subject.getNextEvent();

        assertEquals("HockeyBall", event.getEventName());
        assertEquals("01-02-2024", event.getDate());
        assertEquals("7:00 PM", event.getTime());
    }
}