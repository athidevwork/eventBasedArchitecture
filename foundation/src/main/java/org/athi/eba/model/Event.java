package org.athi.eba.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Event {
    private Long eventStoreId;

    private String objectType;

    private String contextId;

    private String eventName;

    private String payload;

    private String createdAt;

    private String updatedAt;
}
