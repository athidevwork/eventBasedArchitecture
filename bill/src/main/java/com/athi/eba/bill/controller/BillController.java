package com.athi.eba.bill.controller;

import com.athi.eba.bill.model.Bill;
import com.athi.eba.bill.service.BillService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
@RequestMapping("/api/bill")
public class BillController {

    private static final Logger logger = LoggerFactory.getLogger(BillController.class);
    @Autowired
    BillService billService;

    @PostMapping("/createBill")
    public ResponseEntity<Bill> createPayment(@RequestBody Bill bill) throws JsonProcessingException {
        Bill paymentCreated = billService.createBill(bill);
        logger.debug("In bill controller" + paymentCreated);

        Event event = Event.builder().eventName("Bill Created").contextId(bill.getBillId().toString())
                        .objectType("Bill").payload(bill.toString()).build();
        EventUtil.sendEvent(event);
        return ResponseEntity.ok(paymentCreated);
    }
}