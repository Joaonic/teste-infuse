package com.testes.infuse.orders.infrastructure.persistence.mysql.jpa;

import com.testes.infuse.orders.core.domain.entity.Order;
import com.testes.infuse.orders.core.domain.repository.filter.OrderFilter;
import com.testes.infuse.orders.infrastructure.persistence.mysql.OrderRepositoryAdapter;
import com.testes.infuse.orders.infrastructure.persistence.mysql.jpa.filter.Filter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderRepositoryAdapterTest {

    public static final String CTRL_123 = "123";
    @Mock
    private OrderJpaRepository orderJpaRepository;

    @InjectMocks
    private OrderRepositoryAdapter adapter;

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
        order.setId(1L);
        order.setControlNumber(CTRL_123);
        order.setRegistrationDate(LocalDate.now());
        order.setName("Product X");
        order.setUnitPrice(new BigDecimal("299.99"));
        order.setQuantity(2);
        order.setCustomerCode("CUST123");
        order.setTotalPrice(new BigDecimal("599.98"));
    }

    @Test
    void saveOrder() {
        when(orderJpaRepository.save(any(Order.class))).thenReturn(order);

        Order savedOrder = adapter.save(order);

        verify(orderJpaRepository).save(order);
        assert savedOrder.getControlNumber().equals(CTRL_123);
    }

    @Test
    void findByControlNumber() {
        when(orderJpaRepository.findByControlNumber(CTRL_123)).thenReturn(Optional.of(order));

        Optional<Order> foundOrder = adapter.findByControlNumber(CTRL_123);

        verify(orderJpaRepository).findByControlNumber(CTRL_123);
        assert foundOrder.isPresent();
        assert foundOrder.get().getControlNumber().equals(CTRL_123);
    }

    @Test
    void filterOrders() {
        OrderFilter orderFilter = new Filter();
        Page<Order> page = new PageImpl<>(Collections.singletonList(order));
        when(orderJpaRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);


        Page<Order> result = adapter.filter(orderFilter);

        verify(orderJpaRepository).findAll(any(Specification.class), any(Pageable.class));
        assert result.getContent().size() == 1;
        assert result.getContent().get(0).getControlNumber().equals(CTRL_123);
    }
}
