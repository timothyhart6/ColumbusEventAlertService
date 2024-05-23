package com.ColumbusEventAlertService;

import com.ColumbusEventAlertService.columbusEvents.NationwideArenaEvents;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		NationwideArenaEvents nationwideArenaEvents = new NationwideArenaEvents();
		nationwideArenaEvents.getUpcomingEvent();
	}
}
