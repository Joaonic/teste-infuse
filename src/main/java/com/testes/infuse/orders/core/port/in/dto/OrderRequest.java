package com.testes.infuse.orders.core.port.in.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {

    @NotBlank
    @Pattern(regexp = "\\d+", message = "Order number must be numeric")
    // Despite being a numeric identification, it was considered as a String due to the possibility of being sent with 0 on the left
    private String controlNumber;

    @Builder.Default
    private LocalDate registrationDate = LocalDate.now();

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private BigDecimal unitPrice;

    @Builder.Default
    @Min(1)
    private int quantity = 1;

    @NotBlank
    @Pattern(regexp = "\\d+", message = "Customer code must be numeric")
    // Despite being a numeric identification, it was considered as a String due to the possibility of being sent with 0 on the left
    private String customerCode;

}