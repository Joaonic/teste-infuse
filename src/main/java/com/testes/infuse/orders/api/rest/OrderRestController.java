package com.testes.infuse.orders.api.rest;


import com.testes.infuse.orders.api.shared.mapper.OrderRequestMapper;
import com.testes.infuse.orders.api.shared.request.Orders;
import com.testes.infuse.orders.core.port.in.OrderRequestUseCase;
import com.testes.infuse.orders.core.port.in.dto.OrderDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderRequestUseCase portIn;


    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<OrderDto> create(@RequestBody @Valid Orders orders) {
        var orderDtos = OrderRequestMapper.INSTANCE.orderRequestsToDtos(orders.getOrderList());

        return portIn.createOrders(orderDtos);
    }

    @GetMapping(path = "/{control_number}", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public OrderDto retrieve(@PathVariable(value = "control_number") @Pattern(regexp = "\\d+", message = "Order number must be numeric") String controlNumber) {
        return portIn.retrieveOrder(controlNumber);
    }
}
