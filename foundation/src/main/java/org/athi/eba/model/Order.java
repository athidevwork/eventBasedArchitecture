package org.athi.eba.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Order {
    private Long orderId;

    private String customerName;

    private BigDecimal totalAmount;

    private String createdAt;

    private String updatedAt;
}
