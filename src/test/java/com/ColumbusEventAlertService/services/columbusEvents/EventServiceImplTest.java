package com.ColumbusEventAlertService.services.columbusEvents;

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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceImplTest {
    @InjectMocks
    private NationwideEventServiceImpl subject;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private JsoupService jsoupService;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Connection connection;
    @Mock
    private DateUtil dateUtil;
    private final String url = "TestingUrl";

    @BeforeEach
    public void setUp() {
        subject = new NationwideEventServiceImpl(url, jsoupService, dateUtil, "Parent class");
    }

    @Test
    public void testGetUpcomingEvent_invalidUrl() throws Exception{
        when(jsoupService.connect(anyString())).thenThrow(IllegalArgumentException.class);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> subject.getUpcomingEvent());
        assertEquals("Invalid URL: " + url, exception.getMessage());
    }

    @Test
    public void testGetUpcomingEvent_ioException() throws Exception {
        when(jsoupService.connect(anyString())).thenReturn(connection);
        when(jsoupService.getDocument(connection.userAgent(anyString()))).thenThrow(IllegalArgumentException.class);
        NationwideEventServiceImpl eventServiceImpl = new NationwideEventServiceImpl(url, jsoupService, dateUtil, "Nationwide Arena");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> eventServiceImpl.getUpcomingEvent());

        assertEquals("Invalid URL: " + url, exception.getMessage());
    }
}
