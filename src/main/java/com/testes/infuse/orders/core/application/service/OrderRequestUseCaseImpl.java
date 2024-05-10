package com.testes.infuse.orders.core.application.service;

import com.testes.infuse.orders.core.application.mapper.OrderDtoMapper;
import com.testes.infuse.orders.core.domain.service.OrderService;
import com.testes.infuse.orders.core.port.in.OrderRequestUseCase;
import com.testes.infuse.orders.core.port.in.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderRequestUseCaseImpl implements OrderRequestUseCase {


    private final OrderService orderService;


    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        var order = OrderDtoMapper.INSTANCE.orderDtoToEntity(orderDto);

        order = orderService.save(order);

        return OrderDtoMapper.INSTANCE.orderToDto(order);
    }
}
