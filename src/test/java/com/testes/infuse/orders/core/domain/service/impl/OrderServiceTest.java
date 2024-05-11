package com.testes.infuse.orders.core.domain.service.impl;

import com.testes.infuse.orders.core.domain.entity.Order;
import com.testes.infuse.orders.core.domain.exception.DuplicateControlNumberException;
import com.testes.infuse.orders.core.domain.exception.NotFoundException;
import com.testes.infuse.orders.core.domain.repository.OrderRepository;
import com.testes.infuse.orders.core.domain.repository.filter.OrderFilter;
import com.testes.infuse.orders.core.domain.service.OrderService;
import com.testes.infuse.orders.infrastructure.persistence.mysql.jpa.filter.Filter;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private OrderService orderService;
    private OrderRepository orderRepository;

    @Before
    public void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderServiceImpl(orderRepository);
    }

    @Test
    public void testSaveOrderValidCustomer() {
        Order order = new Order();
        order.setControlNumber("12345");
        order.setCustomerCode("5");

        when(orderRepository.findByControlNumber("12345")).thenReturn(Optional.empty());
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order savedOrder = orderService.save(order);
        assertNotNull(savedOrder);
        assertEquals("12345", savedOrder.getControlNumber());

        verify(orderRepository).save(order);
    }

    @Test(expected = NotFoundException.class)
    public void testSaveOrderInvalidCustomer() {
        Order order = new Order();
        order.setControlNumber("12345");
        order.setCustomerCode("15");

        orderService.save(order);
    }

    @Test(expected = DuplicateControlNumberException.class)
    public void testSaveOrderDuplicateControlNumber() {
        Order order = new Order();
        order.setControlNumber("12345");
        order.setCustomerCode("5");

        when(orderRepository.findByControlNumber("12345")).thenReturn(Optional.of(new Order()));

        orderService.save(order);
    }

    @Test
    public void testFindByControlNumber() {
        Order order = new Order();
        order.setControlNumber("12345");

        when(orderRepository.findByControlNumber("12345")).thenReturn(Optional.of(order));

        Order foundOrder = orderService.findByControlNumber("12345");
        assertNotNull(foundOrder);
        assertEquals("12345", foundOrder.getControlNumber());
    }

    @Test(expected = NotFoundException.class)
    public void testFindByControlNumberNotFound() {
        when(orderRepository.findByControlNumber("12345")).thenReturn(Optional.empty());

        orderService.findByControlNumber("12345");
    }

    @Test
    public void testFilterOrders() {
        OrderFilter filter = Filter.builder().build(); // Assuming it has some criteria
        Page<Order> expectedPage = new PageImpl<>(Collections.singletonList(new Order()));

        when(orderRepository.filter(filter)).thenReturn(expectedPage);

        Page<Order> result = orderService.filter(filter);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
