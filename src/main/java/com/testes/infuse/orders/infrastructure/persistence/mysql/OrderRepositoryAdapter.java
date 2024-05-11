package com.testes.infuse.orders.infrastructure.persistence.mysql;

import com.testes.infuse.orders.core.domain.entity.Order;
import com.testes.infuse.orders.core.domain.repository.OrderRepository;
import com.testes.infuse.orders.core.domain.repository.filter.OrderFilter;
import com.testes.infuse.orders.infrastructure.persistence.mysql.jpa.OrderJpaRepository;
import com.testes.infuse.orders.infrastructure.persistence.mysql.jpa.filter.Filter;
import com.testes.infuse.orders.infrastructure.persistence.mysql.jpa.specification.OrderSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    @Override
    public Page<Order> filter(OrderFilter orderFilter) {

        Filter jpaFilter = (Filter) orderFilter;

        OrderSpecification postSpecification = new OrderSpecification();

        Specification<Order> build = postSpecification.build(jpaFilter);

        Pageable pageable = postSpecification.buildPageable(jpaFilter);
        return orderJpaRepository.findAll(build, pageable);
    }
}
