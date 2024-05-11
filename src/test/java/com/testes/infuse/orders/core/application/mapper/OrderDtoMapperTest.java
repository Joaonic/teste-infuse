package com.testes.infuse.orders.core.application.mapper;

import com.testes.infuse.orders.core.domain.entity.Order;
import com.testes.infuse.orders.core.port.in.dto.OrderRequest;
import com.testes.infuse.orders.core.port.in.dto.OrderResponseDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class OrderDtoMapperTest {

    @Test
    void shouldMapOrderToDto() {
        // Given
        Order order = new Order();
        order.setId(1L);
        order.setControlNumber("001");
        order.setRegistrationDate(LocalDate.of(2024, 5, 10));
        order.setName("Sample Order");
        order.setUnitPrice(BigDecimal.valueOf(200.00));
        order.setQuantity(3);
        order.setCustomerCode("C123");
        order.setTotalPrice(BigDecimal.valueOf(600.00));

        // When
        OrderResponseDto orderDto = OrderDtoMapper.INSTANCE.orderToDto(order);

        // Then
        assertThat(orderDto).isNotNull();
        assertThat(orderDto.getId()).isEqualTo(order.getId());
        assertThat(orderDto.getControlNumber()).isEqualTo(order.getControlNumber());
        assertThat(orderDto.getRegistrationDate()).isEqualTo(order.getRegistrationDate());
        assertThat(orderDto.getName()).isEqualTo(order.getName());
        assertThat(orderDto.getUnitPrice()).isEqualTo(order.getUnitPrice());
        assertThat(orderDto.getQuantity()).isEqualTo(order.getQuantity());
        assertThat(orderDto.getCustomerCode()).isEqualTo(order.getCustomerCode());
        assertThat(orderDto.getTotalPrice()).isEqualTo(order.getTotalPrice());
    }

    @Test
    void shouldMapDtoToOrder() {
        // Given
        OrderRequest orderDto = new OrderRequest();
        orderDto.setControlNumber("002");
        orderDto.setRegistrationDate(LocalDate.of(2024, 5, 11));
        orderDto.setName("Another Order");
        orderDto.setUnitPrice(BigDecimal.valueOf(100.00));
        orderDto.setQuantity(5);
        orderDto.setCustomerCode("C456");

        // When
        Order order = OrderDtoMapper.INSTANCE.orderRequestToEntity(orderDto);

        // Then
        assertThat(order).isNotNull();
        assertThat(order.getControlNumber()).isEqualTo(orderDto.getControlNumber());
        assertThat(order.getRegistrationDate()).isEqualTo(orderDto.getRegistrationDate());
        assertThat(order.getName()).isEqualTo(orderDto.getName());
        assertThat(order.getUnitPrice()).isEqualTo(orderDto.getUnitPrice());
        assertThat(order.getQuantity()).isEqualTo(orderDto.getQuantity());
        assertThat(order.getCustomerCode()).isEqualTo(orderDto.getCustomerCode());
    }
}
