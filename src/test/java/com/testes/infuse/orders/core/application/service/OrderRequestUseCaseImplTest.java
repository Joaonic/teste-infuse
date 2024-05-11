package com.testes.infuse.orders.core.application.service;

import com.testes.infuse.orders.core.domain.entity.Order;
import com.testes.infuse.orders.core.domain.exception.InvalidDataException;
import com.testes.infuse.orders.core.domain.repository.filter.OrderFilter;
import com.testes.infuse.orders.core.domain.service.OrderService;
import com.testes.infuse.orders.core.port.in.dto.OrderRequest;
import com.testes.infuse.orders.core.port.in.dto.OrderResponseDto;
import com.testes.infuse.orders.infrastructure.persistence.mysql.jpa.filter.Filter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderRequestUseCaseImplTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderRequestUseCaseImpl orderRequestUseCase;

    private OrderRequest orderRequest;
    private Order order;
    private OrderResponseDto orderResponseDto;

    @BeforeEach
    void setUp() {
        orderRequest = OrderRequest.builder()
                .controlNumber("123")
                .registrationDate(LocalDate.now())
                .name("Sample Product")
                .unitPrice(BigDecimal.valueOf(100))
                .quantity(1)
                .customerCode("001")
                .build();

        order = new Order();
        order.setId(1L);
        order.setControlNumber("123");
        order.setRegistrationDate(LocalDate.now());
        order.setName("Sample Product");
        order.setUnitPrice(BigDecimal.valueOf(100));
        order.setQuantity(1);
        order.setCustomerCode("001");
        order.setTotalPrice(BigDecimal.valueOf(100));

        orderResponseDto = OrderResponseDto.builder()
                .id(order.getId())
                .controlNumber(order.getControlNumber())
                .registrationDate(order.getRegistrationDate())
                .name(order.getName())
                .unitPrice(order.getUnitPrice())
                .quantity(order.getQuantity())
                .customerCode(order.getCustomerCode())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    @Test
    void createOrderSuccess() {
        when(orderService.save(any(Order.class))).thenReturn(order);

        OrderResponseDto response = orderRequestUseCase.createOrder(orderRequest);

        assertNotNull(response);
        assertEquals(order.getId(), response.getId());
        assertEquals(orderResponseDto, response);

        verify(orderService).save(any(Order.class));
    }

    @Test
    void createOrdersExceedsMaximum() {
        List<OrderRequest> orderRequests = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            orderRequests.add(orderRequest);
        }

        assertThrows(InvalidDataException.class, () -> orderRequestUseCase.createOrders(orderRequests));
    }

    @Test
    void createOrdersSuccess() {
        when(orderService.save(any(Order.class))).thenReturn(order);

        List<OrderRequest> orderRequests = List.of(orderRequest, orderRequest);
        List<OrderResponseDto> responses = orderRequestUseCase.createOrders(orderRequests);

        assertEquals(2, responses.size());
        assertEquals(order.getId(), responses.get(0).getId());
        verify(orderService, times(2)).save(any(Order.class));
    }

    @Test
    void retrieveOrderSuccess() {
        when(orderService.findByControlNumber("123")).thenReturn(order);

        OrderResponseDto response = orderRequestUseCase.retrieveOrder("123");

        assertNotNull(response);
        assertEquals(order.getId(), response.getId());
        verify(orderService).findByControlNumber("123");
    }

    @Test
    void filterOrdersSuccess() {
        Page<Order> page = new PageImpl<>(List.of(order));
        when(orderService.filter(any(OrderFilter.class))).thenReturn(page);

        Page<OrderResponseDto> response = orderRequestUseCase.filter(Filter.builder().build());

        assertEquals(1, response.getTotalElements());
        assertEquals(order.getId(), response.getContent().get(0).getId());
        verify(orderService).filter(any(OrderFilter.class));
    }
}
