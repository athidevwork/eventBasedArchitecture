package com.athi.eba.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PreDestroy;
import org.athi.eba.event.EventUtil;
import org.athi.eba.model.Event;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationShutdownHandlers;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class OrderApplication {

	@Value("${spring.application.name}")
	private String appName;
	@Value("${server.port}")
	private String appPort;

	private static final Logger logger = LoggerFactory.getLogger(OrderApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void afterInit() throws JsonProcessingException {
		logger.debug ("Order Application started at - " + appName + ":" + appPort);
		Event event = Event.builder().eventName("Order Service Started").contextId("0").objectType("Order")
				.payload(appName + ":" + appPort + " Started.").build();
		EventUtil.sendEvent(event);
	}

	@PreDestroy
	public void onExit() throws JsonProcessingException {
		logger.debug ("Order Application shutdown at - " + appName + ":" + appPort);
		Event event = Event.builder().eventName("Order Service Shutdown").contextId("0").objectType("Order")
				.payload(appName + ":" + appPort + " Shutdown.").build();
		EventUtil.sendEvent(event);
	}
}
