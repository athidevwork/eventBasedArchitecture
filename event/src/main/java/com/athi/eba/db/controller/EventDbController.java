package com.athi.eba.db.controller;

import com.athi.eba.db.model.Event;
import com.athi.eba.db.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
public class EventDbController {

    private static final Logger logger = LoggerFactory.getLogger(EventDbController.class);

    @Autowired
    EventService eventService;

    @PostMapping("/saveEvent")
    public ResponseEntity<Event> saveEvent(@RequestBody Event event) {
        logger.debug("Event Db controller with event = " + event);
        Event evt = eventService.createEvent(event);
        return ResponseEntity.ok(evt);
    }
}