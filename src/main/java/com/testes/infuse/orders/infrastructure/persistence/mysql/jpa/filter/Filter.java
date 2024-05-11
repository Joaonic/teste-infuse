package com.testes.infuse.orders.infrastructure.persistence.mysql.jpa.filter;

import com.testes.infuse.orders.core.domain.repository.filter.OrderFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Filter implements IFilter, OrderFilter {
    @Builder.Default
    private Boolean simplified = false;
    @Builder.Default
    private Integer page = 0;
    @Builder.Default
    private Integer pageSize = 16;
    @Builder.Default
    private String sort = "id";
    @Builder.Default
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    @Builder.Default
    private String simplifiedField = "";
    @Builder.Default
    private Map<String, Sort.Direction> sortMap = new HashMap<>();


    private String controlNumber;

    private LocalDate startRegistrationDate;
    private LocalDate endRegistrationDate;

    private String name;

    private BigDecimal unitPrice;

    private Integer quantity;

    private String customerCode;

    private BigDecimal totalPrice;
}
