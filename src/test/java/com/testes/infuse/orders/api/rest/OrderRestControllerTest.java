package com.testes.infuse.orders.api.rest;

import com.testes.infuse.orders.api.shared.request.FilterRequest;
import com.testes.infuse.orders.api.shared.request.OrderList;
import com.testes.infuse.orders.core.port.in.OrderRequestUseCase;
import com.testes.infuse.orders.core.port.in.dto.OrderRequest;
import com.testes.infuse.orders.core.port.in.dto.OrderResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderRestControllerTest {

    @Mock
    private OrderRequestUseCase orderRequestUseCase;

    @InjectMocks
    private OrderRestController orderRestController;

    @Test
    void testCreateOrders() {
        // Given
        List<OrderResponseDto> expectedOrders = List.of(new OrderResponseDto());
        List<OrderRequest> requests = List.of(new OrderRequest());
        OrderList orderList = new OrderList();
        orderList.setOrders(List.of(new OrderRequest()));

        when(orderRequestUseCase.createOrders(any())).thenReturn(expectedOrders);

        // When
        List<OrderResponseDto> result = orderRestController.create(orderList);

        // Then
        assertEquals(expectedOrders, result);
        verify(orderRequestUseCase).createOrders(requests);
    }

    @Test
    void testFilterOrders() {
        FilterRequest filterRequest = new FilterRequest();
        Page<OrderResponseDto> expectedPage = mock(Page.class);

        when(orderRequestUseCase.filter(any())).thenReturn(expectedPage);

        Page<OrderResponseDto> result = orderRestController.filter(filterRequest);

        assertEquals(expectedPage, result);
        verify(orderRequestUseCase).filter(any());
    }
}
