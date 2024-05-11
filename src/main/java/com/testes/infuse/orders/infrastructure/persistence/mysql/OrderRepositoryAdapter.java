package com.testes.infuse.orders.infrastructure.persistence.mysql;

import com.testes.infuse.orders.core.domain.entity.Order;
import com.testes.infuse.orders.core.domain.repository.OrderRepository;
import com.testes.infuse.orders.infrastructure.persistence.mysql.jpa.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;


    @Override
    public Order save(Order order) {
        return orderJpaRepository.save(order);
    }

    @Override
    public Optional<Order> findByControlNumber(String controlNumber) {
        return orderJpaRepository.findByControlNumber(controlNumber);
    }
}
