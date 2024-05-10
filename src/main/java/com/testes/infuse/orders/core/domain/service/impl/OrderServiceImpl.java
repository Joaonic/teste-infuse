package com.testes.infuse.orders.core.domain.service.impl;

import com.testes.infuse.orders.core.domain.entity.Order;
import com.testes.infuse.orders.core.domain.service.OrderService;
import com.testes.infuse.orders.infrastructure.persistence.mysql.jpa.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService, CommandLineRunner {

    private static final NavigableMap<Integer, BigDecimal> discountMap = new TreeMap<>();

    static {
        discountMap.put(1, BigDecimal.valueOf(1));
        discountMap.put(5, BigDecimal.valueOf(0.95));
        discountMap.put(10, BigDecimal.valueOf(0.90));
    }

    private final OrderJpaRepository orderRepository;

    @Override
    public Order save(Order order) {
        checkDuplicateControlNumber(order.getControlNumber());
        order.setTotalPrice(getTotalPrice(order.getUnitPrice(), order.getQuantity()));
        return orderRepository.save(order);
    }

    public BigDecimal getTotalPrice(BigDecimal unitPrice, int quantity) {
        BigDecimal base = unitPrice.multiply(BigDecimal.valueOf(quantity));
        BigDecimal discount = discountMap.floorEntry(quantity).getValue();
        return base.multiply(discount).setScale(2, RoundingMode.UP);
    }

    private void checkDuplicateControlNumber(String controlNumber) {
        Optional<Order> existingOrder = orderRepository.findByControlNumber(controlNumber);
        if (existingOrder.isPresent()) {
            throw new RuntimeException("Control number already registered.");
        }
    }

    @Override
    public void run(String... args) throws Exception {
        Order order = new Order();

        // Setting fields with sample data
        order.setControlNumber("123456789");  // Unique control number
        order.setName("Sample Product");  // Name of the product or order
        order.setUnitPrice(new BigDecimal("29.99"));  // Price per unit
        order.setQuantity(7);  // Quantity of items
        order.setCustomerCode("987654321");

        save(order);
    }
}