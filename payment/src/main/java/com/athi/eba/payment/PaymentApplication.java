package com.athi.eba.payment;

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
public class PaymentApplication {

	@Value("${spring.application.name}")
	private String appName;
	@Value("${server.port}")
	private String appPort;

	private static final Logger logger = LoggerFactory.getLogger(PaymentApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void afterInit() throws JsonProcessingException {
		logger.debug ("Payment Application started at - " + appName + ":" + appPort);
		Event event = Event.builder().eventName("Payment Service Started").contextId("0").objectType("Payment")
				.payload(appName + ":" + appPort + " Started.").build();
		EventUtil.sendEvent(event);
	}

	@PreDestroy
	public void onExit() throws JsonProcessingException {
		logger.debug ("Payment Application shutdown at - " + appName + ":" + appPort);
		Event event = Event.builder().eventName("Payment Service Shutdown").contextId("0").objectType("Payment")
				.payload(appName + ":" + appPort + " Shutdown.").build();
		EventUtil.sendEvent(event);
	}
}
