package com.ColumbusEventAlertService;

import com.ColumbusEventAlertService.services.TextMessageService;
import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.class})
@Slf4j
public class Application {

	public static void main(String[] args) {
		log.info("Application starting");
		SpringApplication.run(Application.class, args);
	}
}

/*@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.class})
@Slf4j
public class Application {

	public static void main(String[] args) {
		log.info("Application starting");

		ApplicationContext context = SpringApplication.run(Application.class, args);
		context.getBean(TextMessageService.class).sendTodaysEvents();
	}
}*/
