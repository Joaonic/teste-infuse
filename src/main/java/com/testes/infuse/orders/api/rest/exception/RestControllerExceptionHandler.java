package com.testes.infuse.orders.api.rest.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.testes.infuse.orders.core.domain.exception.DuplicateControlNumberException;
import com.testes.infuse.orders.core.domain.exception.InvalidDataException;
import com.testes.infuse.orders.core.domain.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalError(Exception e) {
        return new ErrorResponse("Internal Error!", e.getMessage(), null);
    }

    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidDataException(InvalidDataException e) {
        return new ErrorResponse("Invalid data", e.getMessage(), null);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException e) {
        return new ErrorResponse("Not found", e.getMessage(), null);
    }

    @ExceptionHandler(DuplicateControlNumberException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDuplicateControlNumberException(DuplicateControlNumberException e) {
        return new ErrorResponse("Duplicate control number", e.getMessage(), null);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<ValidationField> validationFields = new ArrayList<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        fieldErrors.forEach(fieldError -> validationFields.add(
                        new ValidationField(
                                fieldError.getDefaultMessage(),
                                fieldError.getField().replace("get", "")
                                        .replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                                        .replaceAll("([a-z])([A-Z])", "$1_$2"),
                                fieldError.getRejectedValue()
                        )
                )
        );

        return new ResponseEntity<>(new ErrorResponse("Fields invalid!", "Validate the fields and try again.", validationFields), HttpStatus.BAD_REQUEST);
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public record ValidationField(String field, String message, Object value) {

    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public record ErrorResponse(String error, String message, List<ValidationField> errors) {

    }
}
