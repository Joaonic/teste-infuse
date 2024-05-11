package com.testes.infuse.orders.api.shared.mapper;

import com.testes.infuse.orders.api.shared.request.FilterRequest;
import com.testes.infuse.orders.infrastructure.persistence.mysql.jpa.filter.Filter;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderRequestMapperTest {

    @Test void shouldMapFilterRequestToFilter() {
        // Given
        Map<String, Sort.Direction> sortMap = new HashMap<>();
        sortMap.put("price", Sort.Direction.DESC);

        FilterRequest filterRequest = getFilterRequest(sortMap);

        // When
        Filter filter = OrderRequestMapper.INSTANCE.filterRequestToFilter(filterRequest);

        // Then
        assertThat(filter).isNotNull();
        assertThat(filter.getSimplified()).isEqualTo(filterRequest.getSimplified());
        assertThat(filter.getPage()).isEqualTo(filterRequest.getPage());
        assertThat(filter.getPageSize()).isEqualTo(filterRequest.getPageSize());
        assertThat(filter.getSort()).isEqualTo(filterRequest.getSort());
        assertThat(filter.getSortDirection()).isEqualTo(filterRequest.getSortDirection());
        assertThat(filter.getSimplifiedField()).isEqualTo(filterRequest.getSimplifiedField());
        assertThat(filter.getSortMap()).isEqualTo(filterRequest.getSortMap());
        assertThat(filter.getControlNumber()).isEqualTo(filterRequest.getControlNumber());
        assertThat(filter.getStartRegistrationDate()).isEqualTo(filterRequest.getStartRegistrationDate());
        assertThat(filter.getEndRegistrationDate()).isEqualTo(filterRequest.getEndRegistrationDate());
        assertThat(filter.getName()).isEqualTo(filterRequest.getName());
        assertThat(filter.getUnitPrice()).isEqualTo(filterRequest.getUnitPrice());
        assertThat(filter.getQuantity()).isEqualTo(filterRequest.getQuantity());
        assertThat(filter.getCustomerCode()).isEqualTo(filterRequest.getCustomerCode());
        assertThat(filter.getTotalPrice()).isEqualTo(filterRequest.getTotalPrice());
    }

    private static @NotNull FilterRequest getFilterRequest(Map<String, Sort.Direction> sortMap) {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setSimplified(true);
        filterRequest.setPage(1);
        filterRequest.setPageSize(20);
        filterRequest.setSort("name");
        filterRequest.setSortDirection(Sort.Direction.ASC);
        filterRequest.setSimplifiedField("productName");
        filterRequest.setSortMap(sortMap);
        filterRequest.setControlNumber("123");
        filterRequest.setStartRegistrationDate(LocalDate.of(2023, 1, 1));
        filterRequest.setEndRegistrationDate(LocalDate.of(2023, 12, 31));
        filterRequest.setName("Test Product");
        filterRequest.setUnitPrice(new BigDecimal("19.99"));
        filterRequest.setQuantity(5);
        filterRequest.setCustomerCode("456");
        filterRequest.setTotalPrice(new BigDecimal("99.95"));
        return filterRequest;
    }
}
