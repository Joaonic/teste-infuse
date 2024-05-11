package com.testes.infuse.orders.api.shared.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {

    @JsonProperty("control_number")
    @JacksonXmlProperty(localName = "control_number")
    @NotBlank
    @Pattern(regexp = "\\d+", message = "Order number must be numeric")
    // Apesar de ser uma identificação númerica, foi considerado como String por conta da possibilidade de serem enviados com 0 a esquerda
    private String controlNumber;

    @Builder.Default
    @JsonProperty("registration_date")
    @JacksonXmlProperty(localName = "registration_date")
    private LocalDate registrationDate = LocalDate.now();

    @NotBlank
    private String name;

    @JsonProperty("unit_price")
    @JacksonXmlProperty(localName = "unit_price")
    @NotNull
    private BigDecimal unitPrice;

    @Builder.Default
    private int quantity = 1;

    @JsonProperty("customer_code")
    @JacksonXmlProperty(localName = "customer_code")
    @NotBlank
    @Pattern(regexp = "\\d+", message = "Customer code must be numeric")
    private String customerCode;

    @JsonProperty("total_price")
    @JacksonXmlProperty(localName = "total_price")
    private BigDecimal totalPrice;
}