package com.ColumbusEventAlertService;

import com.ColumbusEventAlertService.services.TextMessageService;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class LocalRunner {

    @Bean
    @Profile("local")
    public CommandLineRunner runLocal(TextMessageService textMessageService) {
        return args -> {
            System.out.println("Running LOCAL bootstrap logic...");
            textMessageService.sendTodaysEvents();
        };
    }
}