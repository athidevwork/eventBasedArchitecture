package com.athi.eba.notify.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "notify")
@Data
public class Notify {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notifyId;

    private String orderNumber;

    private String customerName;

    private BigDecimal orderAmount;

    private String paymentNumber;

    private BigDecimal paymentAmount;

    private String createdAt;

    private String updatedAt;
}
