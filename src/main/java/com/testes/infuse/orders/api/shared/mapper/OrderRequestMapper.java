package com.testes.infuse.orders.api.shared.mapper;

import com.testes.infuse.orders.api.shared.request.FilterRequest;
import com.testes.infuse.orders.infrastructure.persistence.mysql.jpa.filter.Filter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderRequestMapper {
    OrderRequestMapper INSTANCE = Mappers.getMapper(OrderRequestMapper.class);


    Filter filterRequestToFilter(FilterRequest filterRequest);
}
