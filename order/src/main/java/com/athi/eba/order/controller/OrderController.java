package com.athi.eba.order.controller;

import com.athi.eba.order.model.Order;
import com.athi.eba.order.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.athi.eba.api.APIUtil;
import org.athi.eba.event.EventUtil;
import org.athi.eba.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    Gson gson = new Gson();
    @Autowired
    OrderService orderService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) throws JsonProcessingException {
        Order orderCreated = orderService.createOrder(order);
        logger.debug("In order controller" + orderCreated);

        Event event = Event.builder().eventName("Order Created").contextId(order.getOrderId().toString()).
                                    objectType("Order").payload(order.toString()).build();
        EventUtil.sendEvent(event);
        //sendEvent(event);

        String jsonStr = gson.toJson(order);
        APIUtil.callRestApi("Order", "Bill", jsonStr);
        APIUtil.callRestApi("Order", "Report", jsonStr);
        APIUtil.callRestApi("Order", "Notify", jsonStr);
        return ResponseEntity.ok(orderCreated);
    }

    /*private void sendEvent(Event event) throws JsonProcessingException {
        String jsonStr = gson.toJson (event, Event.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String eventUrl = "http://localhost:8083/api/event/saveEvent";
        HttpEntity<String> request =
                new HttpEntity<String>(jsonStr, headers);
        logger.debug("jsonStr = " + jsonStr);
        String resultAsJsonStr =
                restTemplate.postForObject(eventUrl, request, String.class);
        logger.debug("resultAsJsonStr = " + resultAsJsonStr);
        JsonNode root = objectMapper.readTree(resultAsJsonStr);
        logger.debug ("root = " + root.path("name").asText());
        logger.debug("root = " + root);
    }*/
}