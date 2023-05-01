package org.athi.eba.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Payment {
    private Long paymentId;

    private String orderNumber;

    private String customerName;

    private BigDecimal orderAmount;

    private BigDecimal paymentAmount;

    private String createdAt;

    private String updatedAt;
}
