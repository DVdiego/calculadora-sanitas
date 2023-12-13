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
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


class AdditionServiceTest {

    @Mock
    private ExceptionMessages exceptionMessages;

    @InjectMocks
    private AdditionService additionService;

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
    void testPerformOperation_WithTwoNumbers() {
        BigDecimal result = this.additionService.performOperation(
                Arrays.asList(BigDecimal.valueOf(2), BigDecimal.valueOf(3)));

        assertEquals(BigDecimal.valueOf(5), result);
    }

    @Test
    void testPerformOperation_WithMultipleNumbers() {
        BigDecimal result = this.additionService.performOperation(
                Arrays.asList(BigDecimal.valueOf(2), BigDecimal.valueOf(3), BigDecimal.valueOf(5)));

        assertEquals(BigDecimal.valueOf(10), result);
    }

    @Test
    void testPerformOperation_WithEmptyList() {
        assertThrows(InvalidOperationException.class, () ->
                this.additionService.performOperation(Collections.emptyList()));
    }

    @Test
    void testPerformOperation_WithSingleNumber() {
        when(this.exceptionMessages.getMessages()).thenReturn(createExceptionMessagesMap());
        assertThrows(InvalidOperationException.class, () ->
                this.additionService.performOperation(List.of(BigDecimal.valueOf(2))));
    }

    private Map<String, String> createExceptionMessagesMap() {
        Map<String, String> messages = new HashMap<>();
        messages.put("list-numbers-wrong", "The list should contain at least two numbers");
        return messages;
    }

}