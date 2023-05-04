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
import org.springframework.context.event.EventListener;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class EventApplication {

	@Value("${spring.application.name}")
	private String serverName;

	@Value("${server.host:}")
	private String serverHost;
	@Value("${server.port}")
	private String serverPort;

	@Autowired
	private EventService eventService;

	private static final Logger logger = LoggerFactory.getLogger(EventApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(EventApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void afterInit() throws UnknownHostException {
		InetAddress localhost = InetAddress.getLocalHost();
		String ipAddress = localhost.getHostAddress();
		logger.debug ("IP Address: " + ipAddress);
		logger.debug (EventApplication.class.getName() + " started at - " + serverName + ":" + serverPort + ", host = " + serverHost);
		Event event = Event.builder().eventName("EventService").objectType("Event").eventName("Event Service Started")
						.contextId("0").payload(serverName + ":" + serverPort + " Started.").build();
		eventService.createEvent(event);
	}

	/*@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}*/
}
