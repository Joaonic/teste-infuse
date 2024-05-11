package com.testes.infuse.orders.core.port.in.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
@JsonIgnoreProperties(ignoreUnknown = true )
public class OrderResponseDto {

    private Long id;

    private String controlNumber;

    private LocalDate registrationDate = LocalDate.now();

    private String name;


    private BigDecimal unitPrice;


    private int quantity = 1;

    private String customerCode;

    private BigDecimal totalPrice;


}