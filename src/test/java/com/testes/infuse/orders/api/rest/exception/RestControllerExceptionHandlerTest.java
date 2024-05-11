package com.testes.infuse.orders.api.rest.exception;

import com.testes.infuse.orders.core.domain.exception.DuplicateControlNumberException;
import com.testes.infuse.orders.core.domain.exception.InvalidDataException;
import com.testes.infuse.orders.core.domain.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestControllerExceptionHandlerTest {

    private RestControllerExceptionHandler exceptionHandler;
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        exceptionHandler = new RestControllerExceptionHandler();
        webRequest = new ServletWebRequest(new MockHttpServletRequest());
    }

    @Test
    void testHandlehandleInternalError() {
        RuntimeException exception = new RuntimeException("Exceção inesperada");

        RestControllerExceptionHandler.ErrorResponse response = exceptionHandler.handleInternalError(exception);

        assertEquals("Internal Error!", response.error());
        assertEquals("Exceção inesperada", response.message());
    }

    @Test
    void testHandleInvalidDataException() {
        InvalidDataException exception = new InvalidDataException("Invalid data error");

        RestControllerExceptionHandler.ErrorResponse response = exceptionHandler.handleInvalidDataException(exception);

        assertEquals("Invalid data", response.error());
        assertEquals("Invalid data error", response.message());
    }

    @Test
    void testHandleNotFoundException() {
        NotFoundException exception = new NotFoundException("Not found error");

        RestControllerExceptionHandler.ErrorResponse response = exceptionHandler.handleNotFoundException(exception);

        assertEquals("Not found", response.error());
        assertEquals("Not found error", response.message());
    }

    @Test
    void testHandleDuplicateControlNumberException() {
        DuplicateControlNumberException exception = new DuplicateControlNumberException("Duplicate error");

        RestControllerExceptionHandler.ErrorResponse response = exceptionHandler.handleDuplicateControlNumberException(exception);

        assertEquals("Duplicate control number", response.error());
        assertEquals("Duplicate error", response.message());
    }

    @Test
    void testHandleMethodArgumentNotValid() {
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, mock(BindingResult.class));
        when(exception.getBindingResult().getFieldErrors()).thenReturn(List.of(new FieldError("objectName", "fieldName", "rejectedValue", false, null, null, "defaultMessage")));

        ResponseEntity<Object> response = exceptionHandler.handleMethodArgumentNotValid(exception, null, null, webRequest);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        RestControllerExceptionHandler.ErrorResponse errorResponse = (RestControllerExceptionHandler.ErrorResponse) response.getBody();
        assertEquals("Fields invalid!", errorResponse.error());
        assertEquals("Validate the fields and try again.", errorResponse.message());
        assertFalse(errorResponse.errors().isEmpty());
    }
}
