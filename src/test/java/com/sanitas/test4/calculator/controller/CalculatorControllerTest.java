package com.sanitas.test4.calculator.controller;

import com.sanitas.test4.calculator.exception.CalculatorException;
import com.sanitas.test4.calculator.exception.InvalidOperationException;
import com.sanitas.test4.calculator.factory.OperationFactory;
import com.sanitas.test4.calculator.model.BasicOperationRequest;
import com.sanitas.test4.calculator.model.OperationResponse;
import com.sanitas.test4.calculator.service.OperationService;
import io.corp.calculator.TracerAPI;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
class CalculatorControllerTest {

    @Mock
    private OperationFactory operationFactory;

    @Mock
    private TracerAPI tracerAPI;

    @InjectMocks
    private CalculatorController calculatorController;

    private AutoCloseable closeable;
    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void releaseMocks() throws Exception {
        this.closeable.close();
    }

    @Test
    void testPerform_Addition() throws CalculatorException {
        BasicOperationRequest request = new BasicOperationRequest("+", Arrays.asList(BigDecimal.TEN,BigDecimal.TEN));
        OperationService mockOperationService = mock(OperationService.class);
        when(this.operationFactory.getOperation("+")).thenReturn(mockOperationService);
        when(mockOperationService.performOperation(Arrays.asList(BigDecimal.TEN,BigDecimal.TEN))).thenReturn(BigDecimal.valueOf(20));

        ResponseEntity<OperationResponse> response = this.calculatorController.perform(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("10 + 10", Objects.requireNonNull(response.getBody()).getExpression());
        assertEquals(BigDecimal.valueOf(20), response.getBody().getResult());
        verify(this.tracerAPI).trace("Operation performed successfully: 10 + 10 = 20");
    }


    @Test
    void testPerform_Subtraction() throws CalculatorException {
        BasicOperationRequest request = new BasicOperationRequest("-", Arrays.asList(BigDecimal.TEN,BigDecimal.ONE));
        OperationService mockOperationService = mock(OperationService.class);
        when(this.operationFactory.getOperation("-")).thenReturn(mockOperationService);
        when(mockOperationService.performOperation(Arrays.asList(BigDecimal.TEN,BigDecimal.ONE))).thenReturn(BigDecimal.valueOf(9));

        ResponseEntity<OperationResponse> response = this.calculatorController.perform(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("10 - 1", Objects.requireNonNull(response.getBody()).getExpression());
        assertEquals(BigDecimal.valueOf(9), response.getBody().getResult());
        verify(this.tracerAPI).trace("Operation performed successfully: 10 - 1 = 9");
    }

    @Test
    void testPerform_InvalidOperation() {
        BasicOperationRequest request = new BasicOperationRequest("invalidOperation", Collections.singletonList(BigDecimal.TEN));
        when(this.operationFactory.getOperation("invalidOperation")).thenThrow(new InvalidOperationException("Invalid operation"));

        assertThrows(InvalidOperationException.class, () -> this.calculatorController.perform(request));
        verify(this.tracerAPI, never()).trace(anyString());
    }

    @Test
    void testPerform_InternalServerError() {
        BasicOperationRequest request = new BasicOperationRequest("+", Collections.singletonList(BigDecimal.TEN));
        when(this.operationFactory.getOperation("+")).thenThrow(new RuntimeException("Internal Server Error"));

        assertThrows(CalculatorException.class, () -> this.calculatorController.perform(request));
        verify(this.tracerAPI, never()).trace(anyString());
    }
}