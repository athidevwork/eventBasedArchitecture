package com.athi.eba;

import com.athi.eba.db.model.Event;
import com.athi.eba.db.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EventApplication {

	@Value("${spring.application.name}")
	private String appName;
	@Value("${server.port}")
	private String appPort;

	@Autowired
	private EventService eventService;

	private static final Logger logger = LoggerFactory.getLogger(EventApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(EventApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void afterInit() {
		//System.out.println ("TESTING - com.athi.eba.EventApplication started at - " + appName + ":" + appPort);
		logger.debug ("com.athi.eba.EventApplication started at - " + appName + ":" + appPort);
		Event event = Event.builder().eventName("EventService").objectType("Event").eventName("Event Service Started")
						.contextId("0").payload(appName + ":" + appPort + " Started.").build();
		eventService.createEvent(event);
	}

	/*@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}*/
}
