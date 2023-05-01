package org.athi.eba.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Bill {
    private Long billId;

    private String orderNumber;

    private String customerName;

    private BigDecimal orderAmount;

    private String paymentNumber;

    private BigDecimal paymentAmount;

    private String createdAt;

    private String updatedAt;
}
