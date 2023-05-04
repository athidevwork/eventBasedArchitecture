package com.athi.eba.payment.controller;

import com.athi.eba.payment.model.Payment;
import com.athi.eba.payment.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.athi.eba.api.APIUtil;
import org.athi.eba.event.EventUtil;
import org.athi.eba.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    Gson gson = new Gson();
    @Autowired
    PaymentService paymentService;

    @PostMapping("/createPayment")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) throws JsonProcessingException {
        Payment paymentCreated = paymentService.createPayment(payment);
        logger.debug("In payment controller" + paymentCreated);

        Event event = Event.builder().eventName("Payment Created").contextId(payment.getPaymentId().toString())
                        .objectType("Payment").payload(payment.toString()).build();
        EventUtil.sendEvent(event);

        String jsonStr = gson.toJson(payment);
        APIUtil.callRestApi("Payment", "Bill", jsonStr);
        APIUtil.callRestApi("Payment", "Report", jsonStr);
        APIUtil.callRestApi("Payment", "Notify", jsonStr);

        return ResponseEntity.ok(paymentCreated);
    }
}