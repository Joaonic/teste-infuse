package com.testes.infuse.orders.infrastructure.persistence.mysql.jpa;

import com.testes.infuse.orders.core.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    Optional<Order> findByControlNumber(String controlNumber);
}