package com.ColumbusEventAlertService.services.columbusEvents;

import com.ColumbusEventAlertService.models.Event;
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
public class LowerFieldEventServiceImplTest {
    @Mock
    private DateUtil dateUtil;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private JsoupService jsoupService;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Connection connection;

    @InjectMocks
    private LowerFieldEventServiceImpl subject;

    @BeforeEach
    public void setUp() {
        subject = new LowerFieldEventServiceImpl("Test", jsoupService, dateUtil, "Lower.com Field");
    }

    @Test
    public void testGetUpcomingEvent_success() throws Exception {
        when(jsoupService.connect(anyString())).thenReturn(connection);
        //DeepStub all the fields
        when(jsoupService.getDocument(connection.userAgent(anyString())).getElementsByClass("awb-custom-text-color awb-custom-text-hover-color").get(0).childNode(0).toString().trim()).thenReturn("Lower.com Field Event");
        when(jsoupService.getDocument(connection.userAgent(anyString())).getElementsByClass("tribe-event-date-start").get(0).childNode(0).toString().trim()).thenReturn("Jul 22");
        when(dateUtil.convertMonthNameToNumber(anyString())).thenReturn("07");
        when(dateUtil.formatDay(anyString())).thenReturn("22");

        Event event = subject.getUpcomingEvent();

        assertEquals("Lower.com Field Event", event.getEventName());
        assertEquals("07-22-2024", event.getDate());
        assertEquals("Time is not available", event.getTime());
    }
}