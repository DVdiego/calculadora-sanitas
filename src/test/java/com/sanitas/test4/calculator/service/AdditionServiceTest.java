package com.sanitas.test4.calculator.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditionServiceTest {

    private final AdditionService additionService = new AdditionService();

    @Test
    @DisplayName("1 + 4 + 5  = 10")
    void shouldAddNumbersCorrectly() {
        List<BigDecimal> numbers = Arrays.asList(BigDecimal.valueOf(1), BigDecimal.valueOf(4), BigDecimal.valueOf(5));
        BigDecimal result = this.additionService.performOperation(numbers);
        assertEquals(BigDecimal.valueOf(10), result, "1 + 4 + 5  should equal 10");
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "0,    1,   1",
            "1,    2,   3",
            "48,  52, 100",
            "2,  99, 101"
    })
    void add(int first, int second, int expectedResult) {
        List<BigDecimal> numbers = Arrays.asList(BigDecimal.valueOf(first), BigDecimal.valueOf(second));
        BigDecimal result = this.additionService.performOperation(numbers);
        assertEquals(BigDecimal.valueOf(expectedResult), result, () -> first + " + " + second + " should equal " + expectedResult);
    }
}
