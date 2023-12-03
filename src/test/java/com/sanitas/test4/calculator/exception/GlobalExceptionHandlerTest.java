package com.sanitas.test4.calculator.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private GlobalExceptionHandler exceptionHandler;

    @Test
    void handleInvalidOperation() {

        InvalidOperationException invalidOperationException = new InvalidOperationException("Invalid Operation");

        ResponseEntity<String> responseEntity = this.exceptionHandler.handleInvalidOperation(invalidOperationException);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid Operation", responseEntity.getBody());
    }

    @Test
    void handleHttpMessageNotReadableException() {

        HttpMessageNotReadableException exception = mock(HttpMessageNotReadableException.class);
        when(exception.getMessage()).thenReturn("NotReadableException");
        ResponseEntity<String> responseEntity = this.exceptionHandler.handleHttpMessageNotReadableException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("NotReadableException", responseEntity.getBody());
    }

    @Test
    void handleException() {

        Exception exception = new Exception("Internal Server Error");
        ResponseEntity<String> responseEntity = this.exceptionHandler.handleException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Internal Server Error", responseEntity.getBody());
    }
}