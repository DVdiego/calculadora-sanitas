package com.sanitas.test4.calculator.exception;


import com.sanitas.test4.calculator.model.ApiErrorResponse;
import io.corp.calculator.TracerAPI;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private AutoCloseable closeable;
    @Mock
    private TracerAPI tracerAPI;

    @Mock
    private ExceptionMessages exceptionMessages;

    @InjectMocks
    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void releaseMocks() throws Exception {
        this.closeable.close();
    }

    @Test
    void handleInvalidOperation() {
        when(this.exceptionMessages.getMessages()).thenReturn(createExceptionMessagesMap());

        InvalidOperationException ex = new InvalidOperationException("Invalid operation");
        ResponseEntity<ApiErrorResponse> responseEntity = this.exceptionHandler.handleInvalidOperation(ex);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid Operation", Objects.requireNonNull(responseEntity.getBody()).getMessage());
        assertEquals("Invalid operation", responseEntity.getBody().getDetails());
        verify(this.tracerAPI, times(1)).trace(responseEntity);
    }

    @Test
    void handleHttpMessageNotReadableException() {
        when(this.exceptionMessages.getMessages()).thenReturn(createExceptionMessagesMap());

        HttpMessageNotReadableException exception = mock(HttpMessageNotReadableException.class);
        when(exception.getMessage()).thenReturn("Incorrect data format");

        ResponseEntity<ApiErrorResponse> responseEntity = this.exceptionHandler.handleHttpMessageNotReadableException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Incorrect data format", Objects.requireNonNull(responseEntity.getBody()).getMessage());
        assertEquals("Incorrect data format", responseEntity.getBody().getDetails());
        verify(this.tracerAPI, times(1)).trace(responseEntity);
    }

    @Test
    void handleException() {
        when(this.exceptionMessages.getMessages()).thenReturn(createExceptionMessagesMap());

        CalculatorException ex = new CalculatorException("Generic exception");
        ResponseEntity<ApiErrorResponse> responseEntity = this.exceptionHandler.handleException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Internal Server Error", Objects.requireNonNull(responseEntity.getBody()).getMessage());
        assertEquals("Generic exception", responseEntity.getBody().getDetails());
        verify(this.tracerAPI, times(1)).trace(responseEntity);
    }

    private Map<String, String> createExceptionMessagesMap() {
        Map<String, String> messages = new HashMap<>();
        messages.put("invalid-operation", "Invalid Operation");
        messages.put("incorrect-data-format", "Incorrect data format");
        messages.put("generic-exception", "Internal Server Error");
        return messages;
    }
}