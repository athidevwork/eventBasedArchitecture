package com.athi.eba.notify.controller;

import com.athi.eba.notify.model.Notify;
import com.athi.eba.notify.service.NotifyService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

@RestController
@RequestMapping("/api/notify")
public class NotifyController {

    private static final Logger logger = LoggerFactory.getLogger(NotifyController.class);
    @Autowired
    NotifyService notifyService;

    @PostMapping("/createNotify")
    public ResponseEntity<Notify> createNotify(@RequestBody Notify notify) throws JsonProcessingException {
        Notify notifyCreated = notifyService.createNotify(notify);
        logger.debug("In notify controller" + notifyCreated);

        Event event = Event.builder().eventName("Notify Created").contextId(notify.getNotifyId().toString())
                        .objectType("Notify").payload(notify.toString()).build();
        EventUtil.sendEvent(event);
        return ResponseEntity.ok(notifyCreated);
    }
}