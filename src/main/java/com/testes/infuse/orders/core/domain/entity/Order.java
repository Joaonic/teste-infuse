package com.testes.infuse.orders.core.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    // Despite being a numeric identification, it was considered as a String due to the possibility of being sent with 0 on the left
    private String controlNumber;

    @Column(nullable = false, updatable = false)
    private LocalDate registrationDate = LocalDate.now();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private int quantity = 1;

    @Column(nullable = false)
    // Despite being a numeric identification, it was considered as a String due to the possibility of being sent with 0 on the left
    private String customerCode;

    @Column(nullable = false)
    private BigDecimal totalPrice;

}