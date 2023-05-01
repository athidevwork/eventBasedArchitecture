package com.athi.eba.db.service;

import com.athi.eba.db.model.Event;
import com.athi.eba.db.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

import static java.sql.Timestamp.*;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event) {
        event.setCreatedAt(String.valueOf(Timestamp.from(Instant.now())));
        event.setUpdatedAt(String.valueOf(Timestamp.from(Instant.now())));
        return eventRepository.save(event);
    }
}
