package com.sanitas.test4.calculator.exception;

import com.sanitas.test4.calculator.ApiErrorResponse;
import com.sanitas.test4.calculator.ExceptionMessages;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private GlobalExceptionHandler exceptionHandler;

    @Autowired
    private ExceptionMessages exceptionMessages;

    @Test
    void handleInvalidOperation() {

        InvalidOperationException invalidOperationException = new InvalidOperationException(this.exceptionMessages.getMessages().get("invalid-operation"));
        ResponseEntity<ApiErrorResponse> responseEntity = this.exceptionHandler.handleInvalidOperation(invalidOperationException);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(this.exceptionMessages.getMessages().get("invalid-operation"), Objects.requireNonNull(responseEntity.getBody()).getDetails());

    }

    @Test
    void handleHttpMessageNotReadableException() {

        HttpMessageNotReadableException exception = mock(HttpMessageNotReadableException.class);
        when(exception.getMessage()).thenReturn(this.exceptionMessages.getMessages().get("incorrect-data-format"));
        ResponseEntity<ApiErrorResponse> responseEntity = this.exceptionHandler.handleHttpMessageNotReadableException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(this.exceptionMessages.getMessages().get("incorrect-data-format"), Objects.requireNonNull(responseEntity.getBody()).getDetails());
    }

    @Test
    void handleException() {

        Exception exception = mock(Exception.class);
        when(exception.getMessage()).thenReturn(this.exceptionMessages.getMessages().get("generic-exception"));
        ResponseEntity<ApiErrorResponse> responseEntity = this.exceptionHandler.handleException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(this.exceptionMessages.getMessages().get("generic-exception"), Objects.requireNonNull(responseEntity.getBody()).getDetails());
    }
}