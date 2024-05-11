package com.testes.infuse.orders.core.port.in;

import com.testes.infuse.orders.core.domain.repository.filter.OrderFilter;
import com.testes.infuse.orders.core.port.in.dto.OrderRequest;
import com.testes.infuse.orders.core.port.in.dto.OrderResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderRequestUseCase {

    OrderResponseDto createOrder(OrderRequest orderDto);

    List<OrderResponseDto> createOrders(List<OrderRequest> orderDto);

    OrderResponseDto retrieveOrder(String customerNumber);


    Page<OrderResponseDto> filter(OrderFilter filter);


}
