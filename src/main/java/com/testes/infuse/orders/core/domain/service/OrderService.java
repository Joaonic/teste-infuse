package com.testes.infuse.orders.core.domain.service;


import com.testes.infuse.orders.core.domain.entity.Order;
import com.testes.infuse.orders.core.domain.repository.filter.OrderFilter;
import org.springframework.data.domain.Page;

public interface OrderService {

    Order save(Order order);

    Order findByControlNumber(String controlNumber);

    Page<Order> filter(OrderFilter filter);

}