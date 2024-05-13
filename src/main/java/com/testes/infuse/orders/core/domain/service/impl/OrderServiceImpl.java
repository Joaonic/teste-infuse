package com.testes.infuse.orders.core.domain.service.impl;

import com.testes.infuse.orders.core.domain.entity.Order;
import com.testes.infuse.orders.core.domain.exception.DuplicateControlNumberException;
import com.testes.infuse.orders.core.domain.exception.NotFoundException;
import com.testes.infuse.orders.core.domain.repository.OrderRepository;
import com.testes.infuse.orders.core.domain.repository.filter.OrderFilter;
import com.testes.infuse.orders.core.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        if (!customerExists(order.getCustomerCode())) {
            throw new NotFoundException("Customer not found!");
        }

        checkDuplicateControlNumber(order.getControlNumber());
        return orderRepository.save(order);
    }

    @Override
    public Order findByControlNumber(String controlNumber) {
        return orderRepository.findByControlNumber(controlNumber).orElseThrow(() -> new NotFoundException("Order not found!"));
    }

    @Override
    public Page<Order> filter(OrderFilter filter) {
        return orderRepository.filter(filter);
    }

    private void checkDuplicateControlNumber(String controlNumber) {
        Optional<Order> existingOrder = orderRepository.findByControlNumber(controlNumber);
        if (existingOrder.isPresent()) {
            throw new DuplicateControlNumberException("Control number already registered.");
        }
    }

    /**
     * Method should return if customer exists on DB, customer table is created.
     * Now it starts from the premise that it exists and is a code from 1 to 10.
     */
    private boolean customerExists(String customerCode) {
        var numberCode = Integer.parseInt(customerCode);

        return numberCode >= 1 && numberCode <= 10;
    }

}