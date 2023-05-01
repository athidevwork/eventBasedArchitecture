package com.athi.eba.db.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "event_store")
@Data
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventStoreId;

    private String objectType;

    private String contextId;

    private String eventName;

    private String payload;

    private String createdAt;

    private String updatedAt;
}
