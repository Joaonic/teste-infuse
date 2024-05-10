package com.testes.infuse.orders.api.shared.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class OrderRequest {

    @JsonProperty("control_number")
    @NotBlank
    @Pattern(regexp = "\\d+", message = "Customer code must be numeric")
    private String controlNumber;

    @Builder.Default
    @JsonProperty("registration_date")
    private LocalDate registrationDate = LocalDate.now();

    @NotBlank
    private String name;

    @JsonProperty("unit_price")
    @NotNull
    private BigDecimal unitPrice;

    @Builder.Default
    private int quantity = 1;

    @JsonProperty("customer_code")
    @NotBlank
    @Pattern(regexp = "\\d+", message = "Customer code must be numeric")
    private String customerCode;

    @JsonProperty("total_price")
    @NotNull
    private BigDecimal totalPrice;
}