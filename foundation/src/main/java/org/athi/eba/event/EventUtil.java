package org.athi.eba.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.athi.eba.model.Event;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

public class EventUtil {
    private static final Logger logger = LoggerFactory.getLogger(EventUtil.class);
    private static Gson gson = new Gson();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final RestTemplate restTemplate = new RestTemplate();

    public static void sendEvent(Event event) throws JsonProcessingException {
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
    }
}
