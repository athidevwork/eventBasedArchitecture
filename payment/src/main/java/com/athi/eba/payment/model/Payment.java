package com.athi.eba.payment.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private String orderNumber;

    private String customerName;

    private BigDecimal orderAmount;

    private BigDecimal paymentAmount;

    private String createdAt;

    private String updatedAt;
}
