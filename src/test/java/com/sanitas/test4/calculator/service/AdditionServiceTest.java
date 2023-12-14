package com.sanitas.test4.calculator.service;

import com.sanitas.test4.calculator.exception.ExceptionMessages;
import com.sanitas.test4.calculator.exception.InvalidOperationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
    void additionThreeNumbers() {
            List<BigDecimal> numbers = Arrays.asList(BigDecimal.valueOf(1), BigDecimal.valueOf(4), BigDecimal.valueOf(5));
            BigDecimal result = this.additionService.performOperation(numbers);
            assertEquals(BigDecimal.valueOf(10), result, "1 + 4 + 5  should equal 10");
            assertEquals(BigDecimal.valueOf(10), result, "1 + 4 + 5 should equal 10");
    }

    @Test
    void additionNegativeNumbers() {
        List<BigDecimal> numbers = Arrays.asList(BigDecimal.valueOf(-1), BigDecimal.valueOf(-4), BigDecimal.valueOf(-5));
        BigDecimal result = this.additionService.performOperation(numbers);
        assertEquals(BigDecimal.valueOf(-10), result, "(-1) + (-4) + (-5) should equal -10");
    }

    @Test
    void additionZeroAndPositiveNumber() {
        List<BigDecimal> numbers = Arrays.asList(BigDecimal.valueOf(0), BigDecimal.valueOf(7));
        BigDecimal result = this.additionService.performOperation(numbers);
        assertEquals(BigDecimal.valueOf(7), result, "0 + 7 should equal 7");
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "0,    1,   1",
            "1,    2,   3",
            "48,  52, 100",
            "2,  99, 101"
    })
    void shouldAddNumbersCorrectly(int first, int second, int expectedResult) {
        List<BigDecimal> numbers = Arrays.asList(BigDecimal.valueOf(first), BigDecimal.valueOf(second));
        BigDecimal result = this.additionService.performOperation(numbers);
        assertEquals(BigDecimal.valueOf(expectedResult), result, () -> first + " + " + second + " should equal " + expectedResult);
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