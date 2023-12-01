package com.sanitas.test4.calculator.service;

import com.sanitas.test4.calculator.exception.InvalidOperationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SubtractionServiceTest {

    @Autowired
    private SubtractionService subtractionService;

    @Test
    @DisplayName("10 - 12 - 8 = -10")
    void correctSubtractionWithNegativeNumbers() {
        List<BigDecimal> numbers = Arrays.asList(BigDecimal.valueOf(10), BigDecimal.valueOf(12), BigDecimal.valueOf(8));
        BigDecimal result = this.subtractionService.performOperation(numbers);
        assertEquals(BigDecimal.valueOf(-10), result);
    }

    @Test
    void shouldThrowExceptionWithEmptyList() {
        List<BigDecimal> numbers = List.of();
        assertThrows(InvalidOperationException.class, () -> this.subtractionService.performOperation(numbers));
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "0, 1, -1",
            "1, 5, -4",
            "52, 48, 4",
            "99, 2, 97"
    })
    void shouldSubtractNumbersCorrectly(int first, int second, int expectedResult) {
        List<BigDecimal> numbers = Arrays.asList(BigDecimal.valueOf(first), BigDecimal.valueOf(second));
        BigDecimal result = this.subtractionService.performOperation(numbers);
        assertEquals(BigDecimal.valueOf(expectedResult), result, () -> first + " - " + second + " should equal " + expectedResult);
    }

    @Test
    void subtractNegativeNumbers() {
        List<BigDecimal> numbers = Arrays.asList(BigDecimal.valueOf(-10), BigDecimal.valueOf(-12));
        BigDecimal result = this.subtractionService.performOperation(numbers);
        assertEquals(BigDecimal.valueOf(2), result);
    }

    @Test
    void subtractZeroAndPositiveNumber() {
        List<BigDecimal> numbers = Arrays.asList(BigDecimal.valueOf(7), BigDecimal.valueOf(0));
        BigDecimal result = this.subtractionService.performOperation(numbers);
        assertEquals(BigDecimal.valueOf(7), result);
    }

}
