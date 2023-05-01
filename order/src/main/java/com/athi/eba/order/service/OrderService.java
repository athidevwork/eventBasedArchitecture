package com.athi.eba.order.service;

import com.athi.eba.order.model.Order;
import com.athi.eba.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Transactional //(readOnly = false,isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Order createOrder(Order order) {
        //order.setOrderId(Long.valueOf(String.format("%04d", new Random().nextLong(10000))));
        order.setCreatedAt(String.valueOf(Timestamp.from(Instant.now())));
        order.setUpdatedAt(String.valueOf(Timestamp.from(Instant.now())));
        logger.debug("Order = " + order);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
