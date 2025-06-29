package com.ColumbusEventAlertService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.class})
@Slf4j
public class Application {

	public static void main(String[] args) {
		log.info("Application starting");
		SpringApplication.run(Application.class, args);
	}
}