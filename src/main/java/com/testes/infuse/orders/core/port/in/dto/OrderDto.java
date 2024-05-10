package com.testes.infuse.orders.core.port.in.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private String controlNumber;

    @Builder.Default
    private LocalDate registrationDate = LocalDate.now();

    private String name;
    private BigDecimal unitPrice;

    @Builder.Default
    private int quantity = 1;

    private String customerCode;

    private BigDecimal totalPrice;
}