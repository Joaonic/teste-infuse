package com.testes.infuse.orders.api.rest;


import com.testes.infuse.orders.api.shared.mapper.OrderRequestMapper;
import com.testes.infuse.orders.api.shared.request.FilterRequest;
import com.testes.infuse.orders.api.shared.request.OrderList;
import com.testes.infuse.orders.core.port.in.OrderRequestUseCase;
import com.testes.infuse.orders.core.port.in.dto.OrderResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private static final OrderRequestMapper requestMapper = OrderRequestMapper.INSTANCE;

    private final OrderRequestUseCase portIn;


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public List<OrderResponseDto> create(@RequestBody @Valid OrderList orders) {
        return portIn.createOrders(orders.getOrders());
    }

    @GetMapping(path = "/filter", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Page<OrderResponseDto> filter(@Valid FilterRequest filterRequest) {
        var filter = requestMapper.filterRequestToFilter(filterRequest);

        return portIn.filter(filter);
    }
}
