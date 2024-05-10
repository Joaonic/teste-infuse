package com.testes.infuse.orders.core.domain.repository;

import com.testes.infuse.orders.core.domain.entity.Order;

public interface OrderRepository {

    Order save(Order order);
}
