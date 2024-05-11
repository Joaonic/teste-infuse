package com.testes.infuse.orders.api.shared.mapper;

import com.testes.infuse.orders.api.shared.request.FilterRequest;
import com.testes.infuse.orders.api.shared.request.OrderRequest;
import com.testes.infuse.orders.core.port.in.dto.OrderDto;
import com.testes.infuse.orders.infrastructure.persistence.mysql.jpa.filter.Filter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderRequestMapper {
    OrderRequestMapper INSTANCE = Mappers.getMapper(OrderRequestMapper.class);


    OrderDto orderRequestToDto(OrderRequest orderDto);


    List<OrderDto> orderRequestsToDtos(List<OrderRequest> orderDto);

    Filter filterRequestToFilter(FilterRequest filterRequest);
}
