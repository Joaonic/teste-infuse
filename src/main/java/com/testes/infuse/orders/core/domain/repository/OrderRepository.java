package com.testes.infuse.orders.core.domain.repository;

import com.testes.infuse.orders.core.domain.entity.Order;
import com.testes.infuse.orders.core.domain.repository.filter.OrderFilter;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findByControlNumber(String controlNumber);

    Page<Order> filter(OrderFilter orderFilter);
}
