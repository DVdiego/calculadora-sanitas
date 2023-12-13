package com.sanitas.test4.calculator.service;

import com.sanitas.test4.calculator.exception.ExceptionMessages;
import com.sanitas.test4.calculator.exception.InvalidOperationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class SubtractionServiceTest {

    @Mock
    private ExceptionMessages exceptionMessages;

    @InjectMocks
    private SubtractionService subtractionService;

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
    void testPerformOperation_Success() {
        BigDecimal result = subtractionService.performOperation(
                Arrays.asList(BigDecimal.valueOf(5), BigDecimal.valueOf(3)));

        assertEquals(BigDecimal.valueOf(2), result);
    }

    @Test
    void testPerformOperation_WithTwoNumbers() {
        BigDecimal result = subtractionService.performOperation(
                Arrays.asList(BigDecimal.valueOf(8), BigDecimal.valueOf(3)));

        assertEquals(BigDecimal.valueOf(5), result);
    }

    @Test
    void testPerformOperation_WithMultipleNumbers() {
        BigDecimal result = subtractionService.performOperation(
                Arrays.asList(BigDecimal.valueOf(20), BigDecimal.valueOf(3), BigDecimal.valueOf(5)));

        assertEquals(BigDecimal.valueOf(12), result);
    }

    @Test
    void testPerformOperation_WithEmptyList() {
        assertThrows(InvalidOperationException.class, () ->
                subtractionService.performOperation(Collections.emptyList()));
    }

    @Test
    void testPerformOperation_WithSingleNumber() {
        when(this.exceptionMessages.getMessages()).thenReturn(createExceptionMessagesMap());
        assertThrows(InvalidOperationException.class, () ->
                subtractionService.performOperation(Collections.singletonList(BigDecimal.valueOf(2))));
    }

    private Map<String, String> createExceptionMessagesMap() {
        Map<String, String> messages = new HashMap<>();
        messages.put("list-numbers-wrong", "The list should contain at least two numbers");
        return messages;
    }

}