package com.athi.eba.payment.service;

import com.athi.eba.payment.model.Payment;
import com.athi.eba.payment.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        Optional<Payment> optionalOrder = paymentRepository.findById(id);
        return optionalOrder.orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @Transactional //(readOnly = false,isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Payment createPayment(Payment payment) {
        logger.debug("payment in service = " + payment);
        payment.setCreatedAt(String.valueOf(Timestamp.from(Instant.now())));
        payment.setUpdatedAt(String.valueOf(Timestamp.from(Instant.now())));
        logger.debug("Payment = " + payment);
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
