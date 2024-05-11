package com.testes.infuse.orders.core.port.in;

import com.testes.infuse.orders.core.domain.repository.filter.OrderFilter;
import com.testes.infuse.orders.core.port.in.dto.OrderDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderRequestUseCase {

    OrderDto createOrder(OrderDto orderDto);

    List<OrderDto> createOrders(List<OrderDto> orderDto);

    OrderDto retrieveOrder(String customerNumber);


    Page<OrderDto> filter(OrderFilter filter);


}
