package com.testes.infuse.orders.core.domain.repository.filter;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface OrderFilter {
    String getControlNumber();

    LocalDate getStartRegistrationDate();

    LocalDate getEndRegistrationDate();

    String getName();

    BigDecimal getUnitPrice();

    Integer getQuantity();

    String getCustomerCode();

    BigDecimal getTotalPrice();

    void setControlNumber(String controlNumber);

    void setStartRegistrationDate(LocalDate date);

    void setEndRegistrationDate(LocalDate date);

    void setName(String name);

    void setUnitPrice(BigDecimal price);

    void setQuantity(Integer quantity);

    void setCustomerCode(String customerCode);

    void setTotalPrice(BigDecimal price);
}
