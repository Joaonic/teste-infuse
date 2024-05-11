package com.testes.infuse.orders.core.domain.service;


import com.testes.infuse.orders.core.domain.entity.Order;

public interface OrderService {

    Order save(Order order);

    Order findByControlNumber(String controlNumber);

}