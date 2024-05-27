package com.ColumbusEventAlertService.services.columbusEvents;

import com.ColumbusEventAlertService.models.NationwideEvent;
import com.ColumbusEventAlertService.utils.DateUtil;
import org.jsoup.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestNationwideArenaService {
    @Mock
    private DateUtil dateUtil;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private JsoupService jsoupService;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Connection connection;
    @InjectMocks
    private NationwideArenaService nationwideArenaService;
    private String url = "https://www.nationwidearena.com/events";

    @BeforeEach
    public void setUp() {
        nationwideArenaService = new NationwideArenaService(url, jsoupService, dateUtil);
    }

    @Test
    public void testGetUpcomingEvent_success() throws Exception {
        when(dateUtil.convertMonthNameToNumber("January")).thenReturn("01");
        when(jsoupService.connect(anyString())).thenReturn(connection);
        //DeepStub all the fields
        when(jsoupService.getDocument(connection.userAgent(anyString())).select(anyString()).get(anyInt()).getElementsByClass(anyString()).get(0).getElementsByClass("m-date__month").get(0).childNode(0).toString().trim()).thenReturn("January");
        when(jsoupService.getDocument(connection.userAgent(anyString())).select(anyString()).get(anyInt()).getElementsByClass(anyString()).get(0).getElementsByClass("m-date__day").get(0).childNode(0).toString().replaceAll("\\D", "")).thenReturn("1");
        when(jsoupService.getDocument(connection.userAgent(anyString())).select(anyString()).get(anyInt()).getElementsByClass(anyString()).get(0).getElementsByClass("m-date__year").get(0).childNode(0).toString().replaceAll("\\D", "")).thenReturn("2024");
        when(jsoupService.getDocument(connection.userAgent(anyString())).select(anyString()).get(anyInt()).getElementsByClass(anyString()).get(3).getElementsByClass("start").get(0).childNode(0).toString().trim()).thenReturn("7:00 PM");
        when(jsoupService.getDocument(connection.userAgent(anyString())).select(anyString()).get(anyInt()).getElementsByClass(anyString()).get(0).getElementsByClass("title title-withTagline ").get(0).childNode(1).childNode(0).toString().trim()).thenReturn("HockeyBall");

        NationwideEvent event = nationwideArenaService.getUpcomingEvent();

        assertEquals("HockeyBall", event.getName());
        assertEquals("01-1-2024", event.getDate());
        assertEquals("7:00 PM", event.getTime());
    }

    @Test
    public void testGetUpcomingEvent_invalidUrl() throws Exception{
        // Arrange
        when(jsoupService.connect(anyString())).thenThrow(IllegalArgumentException.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            nationwideArenaService.getUpcomingEvent();
        });
        assertEquals("Invalid URL: " + url, exception.getMessage());
    }

    @Test
    public void testGetUpcomingEvent_ioException() throws Exception {
        when(jsoupService.connect(anyString())).thenReturn(connection);
        when(jsoupService.getDocument(connection.userAgent(anyString())).select(anyString()).get(anyInt()).getElementsByClass(anyString())).thenThrow(IllegalArgumentException.class);
        NationwideArenaService nationwideArenaService = new NationwideArenaService(url, jsoupService, dateUtil);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            nationwideArenaService.getUpcomingEvent();
        });

        assertEquals("Invalid URL: " + url, exception.getMessage());
    }
}