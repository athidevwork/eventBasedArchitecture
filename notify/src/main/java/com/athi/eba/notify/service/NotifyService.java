package com.athi.eba.notify.service;

import com.athi.eba.notify.model.Notify;
import com.athi.eba.notify.repository.NotifyRepository;
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
public class NotifyService {
    private static final Logger logger = LoggerFactory.getLogger(NotifyService.class);
    @Autowired
    private NotifyRepository notifyRepository;

    public List<Notify> getAllNotifys() {
        return notifyRepository.findAll();
    }

    public Notify getBillById(Long id) {
        Optional<Notify> optionalOrder = notifyRepository.findById(id);
        return optionalOrder.orElseThrow(() -> new RuntimeException("Notify not found"));
    }

    @Transactional //(readOnly = false,isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Notify createNotify(Notify notify) {
        logger.debug("notify in service = " + notify);
        notify.setCreatedAt(String.valueOf(Timestamp.from(Instant.now())));
        notify.setUpdatedAt(String.valueOf(Timestamp.from(Instant.now())));
        logger.debug("notify = " + notify);
        return notifyRepository.save(notify);
    }

    public void deleteNotify(Long id) {
        notifyRepository.deleteById(id);
    }
}
