package com.athi.eba.report.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "reports")
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    private String orderNumber;

    private String customerName;

    private BigDecimal orderAmount;

    private String paymentNumber;

    private BigDecimal paymentAmount;

    private String createdAt;

    private String updatedAt;
}
