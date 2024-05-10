package com.testes.infuse.orders.core.port.in;

import com.testes.infuse.orders.core.port.in.dto.OrderDto;

public interface OrderRequestUseCase {

    OrderDto createOrder(OrderDto orderDto) ;


}
