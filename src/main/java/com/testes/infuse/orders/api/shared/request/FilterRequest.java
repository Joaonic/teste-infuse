package com.testes.infuse.orders.api.shared.request;


import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
public class FilterRequest {
    private Boolean simplified = false;

    private Integer page = 0;

    private Integer pageSize = 16;

    private String sort = "id";

    private Sort.Direction sortDirection = Sort.Direction.ASC;

    private String simplifiedField = "";

    private Map<String, Sort.Direction> sortMap = new HashMap<>();

    @Pattern(regexp = "\\d+", message = "Order number must be numeric")
    private String controlNumber;

    private LocalDate startRegistrationDate;

    private LocalDate endRegistrationDate;

    private String name;

    private BigDecimal unitPrice;

    private Integer quantity;

    @Pattern(regexp = "\\d+", message = "Customer code must be numeric")
    private String customerCode;

    private BigDecimal totalPrice;
}
