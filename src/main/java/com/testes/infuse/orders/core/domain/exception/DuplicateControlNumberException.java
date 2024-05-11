package com.testes.infuse.orders.core.domain.exception;

public class DuplicateControlNumberException extends RuntimeException {
    public DuplicateControlNumberException(String message) {
        super(message);
    }
}
