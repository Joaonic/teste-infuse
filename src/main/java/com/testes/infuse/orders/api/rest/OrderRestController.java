package com.testes.infuse.orders.api.rest;


import com.testes.infuse.orders.api.shared.mapper.OrderRequestMapper;
import com.testes.infuse.orders.api.shared.request.OrderRequest;
import com.testes.infuse.orders.core.port.in.OrderRequestUseCase;
import com.testes.infuse.orders.core.port.in.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders/json")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderRequestUseCase portIn;


    @PostMapping
    public OrderDto create(@RequestBody OrderRequest orderRequest) {
        return portIn.createOrder(OrderRequestMapper.INSTANCE.orderRequestToDto(orderRequest));
    }


}