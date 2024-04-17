package com.ColumbusEventAlertService.services;

import com.ColumbusEventAlertService.models.NationwideEvent;
import com.ColumbusEventAlertService.utils.DateUtil;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class TestAlertService {

    TwilioService twilioService = mock(TwilioService.class);
    AlertService alertService = new AlertService();
    NationwideEvent nationwideEvent = new NationwideEvent();
    @Test
    public void textSendsWhenDateIsToday() {
        LocalDate today = LocalDate.now();
        nationwideEvent.setDate(new DateUtil().formatDate(today));

        alertService.sendTodaysEvents(nationwideEvent, twilioService);

        verify(twilioService, times(1)).sendTwilioText(anyString(), anyString());
    }

    @Test
    public void textdoesNotSendWhenPastDate() {
        LocalDate tomorrow = LocalDate.now().minusDays(1);
        nationwideEvent.setDate(String.valueOf(tomorrow));

        alertService.sendTodaysEvents(nationwideEvent, twilioService);

        verify(twilioService, times(0)).sendTwilioText(anyString(), anyString());
    }

    @Test
    public void textdoesNotSendWhenFutureDate() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        nationwideEvent.setDate(String.valueOf(tomorrow));

        alertService.sendTodaysEvents(nationwideEvent, twilioService);

        verify(twilioService, times(0)).sendTwilioText(anyString(), anyString());
    }
}