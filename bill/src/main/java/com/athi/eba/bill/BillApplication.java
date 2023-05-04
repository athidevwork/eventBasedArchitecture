package com.athi.eba.bill;

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
public class BillApplication {

	@Value("${spring.application.name}")
	private String serverName;

	@Value("${server.host}")
	private String serverHost;
	@Value("${server.port}")
	private String serverPort;

	private static final Logger logger = LoggerFactory.getLogger(BillApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BillApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void afterInit() throws JsonProcessingException {
		logger.debug (BillApplication.class.getName() + " started at - " + serverName + ":" + serverPort + ", host = " + serverHost);
		Event event = Event.builder().eventName("Billing Service Started").contextId("0").objectType("Bill")
				.payload(serverName + ":" + serverPort + " Started.").build();
		EventUtil.sendEvent(event);
	}

	@PreDestroy
	public void onExit() throws JsonProcessingException {
		logger.debug ("Bill Application shutdown at - " + serverName + ":" + serverPort);
		Event event = Event.builder().eventName("Bill Service Shutdown").contextId("0").objectType("Bill")
				.payload(serverName + ":" + serverPort + " Shutdown.").build();
		EventUtil.sendEvent(event);
	}
}
