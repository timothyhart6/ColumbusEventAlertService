package com.ColumbusEventAlertService.services.columbusEvents;

import com.ColumbusEventAlertService.models.Event;
import com.ColumbusEventAlertService.services.JsoupService;
import com.ColumbusEventAlertService.services.events.NewportEventService;
import com.ColumbusEventAlertService.utils.DateUtil;
import org.jsoup.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NewportEventServiceTest {
    @Mock
    private DateUtil dateUtil;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private JsoupService jsoupService;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Connection connection;
    @InjectMocks
    private NewportEventService subject;

    @BeforeEach
    public void setUp() {
        subject = new NewportEventService(jsoupService, dateUtil);
    }

    @Test
    public void testGetUpcomingEvent_success() throws Exception {
        when(jsoupService.connect(anyString())).thenReturn(connection);
        //DeepStub all the fields
        when(jsoupService.getDocument(connection.userAgent(anyString())).getElementsByClass("info").get(0).select("h2").get(0).childNode(0).toString()).thenReturn("Newport Event");
        when(jsoupService.getDocument(connection.userAgent(anyString())).getElementsByClass("date").get(0).childNode(3).childNode(1).childNode(0).toString()).thenReturn("January 02");
        when(dateUtil.convertMonthNameToNumber(anyString())).thenReturn("01");
        when(dateUtil.formatDay(anyString())).thenReturn("02");
        when(jsoupService.getDocument(connection.userAgent(anyString())).getElementsByClass("featured-event").get(0).getElementsByClass("doors-time").get(0).childNode(0).toString()).thenReturn("7:00 PM");

        Event event = subject.getNextEvent("Venue Name", "Venue Url");

        assertEquals("Newport Event", event.getEventName());
        assertEquals("01-02-2024", event.getDate());
        assertEquals("7:00 PM", event.getTime());
    }
}