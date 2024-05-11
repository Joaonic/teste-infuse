package com.testes.infuse.orders.core.port.in;

import com.testes.infuse.orders.core.port.in.dto.OrderDto;

import java.util.List;

public interface OrderRequestUseCase {

    OrderDto createOrder(OrderDto orderDto);

    List<OrderDto> createOrders(List<OrderDto> orderDto);

    OrderDto retrieveOrder(String customerNumber);


}
