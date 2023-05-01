package com.athi.eba.bill.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "bills")
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    private String orderNumber;

    private String customerName;

    private BigDecimal orderAmount;

    private String paymentNumber;

    private BigDecimal paymentAmount;

    private String createdAt;

    private String updatedAt;
}
