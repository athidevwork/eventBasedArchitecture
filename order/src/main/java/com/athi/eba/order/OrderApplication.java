package com.athi.eba.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.PreDestroy;
import org.athi.eba.event.EventUtil;
import org.athi.eba.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class OrderApplication {

	@Value("${spring.application.name}")
	private String serverName;

	@Value("${server.host}")
	private String serverHost;
	@Value("${server.port}")
	private String serverPort;

	private static final Logger logger = LoggerFactory.getLogger(OrderApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void afterInit() throws JsonProcessingException {
		logger.debug (OrderApplication.class.getName() + " started at - " + serverName + ":" + serverPort + ", host = " + serverHost);
		Event event = Event.builder().eventName("Order Service Started").contextId("0").objectType("Order")
				.payload(serverName + ":" + serverPort + " Started.").build();
		EventUtil.sendEvent(event);
	}

	@PreDestroy
	public void onExit() throws JsonProcessingException {
		logger.debug ("Order Application shutdown at - " + serverName + ":" + serverPort);
		Event event = Event.builder().eventName("Order Service Shutdown").contextId("0").objectType("Order")
				.payload(serverName + ":" + serverPort + " Shutdown.").build();
		EventUtil.sendEvent(event);
	}
}
