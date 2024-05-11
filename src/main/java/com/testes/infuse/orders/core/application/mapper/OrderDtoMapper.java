package com.testes.infuse.orders.core.application.mapper;

import com.testes.infuse.orders.core.domain.entity.Order;
import com.testes.infuse.orders.core.port.in.dto.OrderRequest;
import com.testes.infuse.orders.core.port.in.dto.OrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderDtoMapper {
    OrderDtoMapper INSTANCE = Mappers.getMapper(OrderDtoMapper.class);


    Order orderRequestToEntity(OrderRequest orderDto);

    OrderResponseDto orderToDto(Order orderDto);
}
