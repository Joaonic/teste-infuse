package com.testes.infuse.orders.core.application.mapper;

import com.testes.infuse.orders.core.domain.entity.Order;
import com.testes.infuse.orders.core.port.in.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderDtoMapper {
    OrderDtoMapper INSTANCE = Mappers.getMapper(OrderDtoMapper.class);


    Order orderDtoToEntity(OrderDto orderDto);

    OrderDto orderToDto(Order orderDto);
}
