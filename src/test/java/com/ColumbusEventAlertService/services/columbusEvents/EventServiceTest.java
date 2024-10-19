package com.ColumbusEventAlertService.services.columbusEvents;

import com.ColumbusEventAlertService.services.JsoupService;
import com.ColumbusEventAlertService.services.events.NationwideEventService;
import com.ColumbusEventAlertService.utils.DateUtil;
import org.jsoup.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    @InjectMocks
    private NationwideEventService subject;
    @Mock
    private JsoupService jsoupService;
    @Mock
    private Connection connection;
    @Mock
    private DateUtil dateUtil;
    private final String url = "Venue Url";

    @BeforeEach
    public void setUp() {
        subject = new NationwideEventService("test", url, jsoupService, dateUtil);
    }

    @Test
    public void testGetNextEvent_invalidUrl() {
        when(jsoupService.connect(anyString())).thenThrow(IllegalArgumentException.class);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> subject.getNextEvent());
        assertEquals("Invalid URL: " + url, exception.getMessage());
    }

    @Test
    public void testGetNextEvent_ioException() throws Exception {
        when(jsoupService.connect(anyString())).thenReturn(connection);
        when(jsoupService.getDocument(connection.userAgent(anyString()))).thenThrow(IOException.class);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> subject.getNextEvent());

        assertEquals("Invalid URL: " + url, exception.getMessage());
    }
}
