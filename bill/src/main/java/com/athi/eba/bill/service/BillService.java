package com.athi.eba.bill.service;

import com.athi.eba.bill.model.Bill;
import com.athi.eba.bill.repository.BillRepository;
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
public class BillService {
    private static final Logger logger = LoggerFactory.getLogger(BillService.class);
    @Autowired
    private BillRepository billRepository;

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Bill getBillById(Long id) {
        Optional<Bill> optionalOrder = billRepository.findById(id);
        return optionalOrder.orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @Transactional //(readOnly = false,isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Bill createBill(Bill bill) {
        logger.debug("bill in service = " + bill);
        bill.setCreatedAt(String.valueOf(Timestamp.from(Instant.now())));
        bill.setUpdatedAt(String.valueOf(Timestamp.from(Instant.now())));
        logger.debug("Bill = " + bill);
        return billRepository.save(bill);
    }

    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }
}
