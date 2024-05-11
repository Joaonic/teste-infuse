package com.testes.infuse.orders.core.application.service;

import com.testes.infuse.orders.core.application.mapper.OrderDtoMapper;
import com.testes.infuse.orders.core.domain.exception.InvalidDataException;
import com.testes.infuse.orders.core.domain.repository.filter.OrderFilter;
import com.testes.infuse.orders.core.domain.service.OrderService;
import com.testes.infuse.orders.core.port.in.OrderRequestUseCase;
import com.testes.infuse.orders.core.port.in.dto.OrderRequest;
import com.testes.infuse.orders.core.port.in.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;



@Service
@RequiredArgsConstructor
public class OrderRequestUseCaseImpl implements OrderRequestUseCase {

    private static final NavigableMap<Integer, BigDecimal> discountMap = new TreeMap<>();

    private static final OrderDtoMapper mapper = OrderDtoMapper.INSTANCE;


    static {
        discountMap.put(1, BigDecimal.valueOf(1));
        discountMap.put(5, BigDecimal.valueOf(0.95));
        discountMap.put(10, BigDecimal.valueOf(0.90));
    }

    private final OrderService orderService;


    @Override
    public OrderResponseDto createOrder(OrderRequest orderDto) {
        var order = mapper.orderRequestToEntity(orderDto);

        order.setTotalPrice(getTotalPrice(order.getUnitPrice(), order.getQuantity()));
        order = orderService.save(order);

        return mapper.orderToDto(order);
    }

    @Override
    public List<OrderResponseDto> createOrders(List<OrderRequest> orderDtos) {

        if (orderDtos.size() > 10) {
            throw new InvalidDataException("Exceeds maximum orders of 10 allowed");
        }

        List<OrderResponseDto> returnOrders = new ArrayList<>();

        for (var orderDto : orderDtos) {
            returnOrders.add(createOrder(orderDto));
        }

        return returnOrders;
    }

    @Override
    public OrderResponseDto retrieveOrder(String customerNumber) {
        var order = orderService.findByControlNumber(customerNumber);

        return mapper.orderToDto(order);
    }

    @Override
    public Page<OrderResponseDto> filter(OrderFilter filter) {
        return orderService.filter(filter).map(mapper::orderToDto);
    }

    public BigDecimal getTotalPrice(BigDecimal unitPrice, int quantity) {
        BigDecimal base = unitPrice.multiply(BigDecimal.valueOf(quantity));
        BigDecimal discount = discountMap.floorEntry(quantity).getValue();
        return base.multiply(discount).setScale(2, RoundingMode.UP);
    }
}
