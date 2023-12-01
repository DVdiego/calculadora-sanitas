package com.sanitas.test4.calculator.service;

import com.sanitas.test4.calculator.exception.InvalidOperationException;
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
public class AdditionServiceTest {

    @Autowired
    private AdditionService additionService;

    @Test
    void additionThreeNumbers() {
        List<BigDecimal> numbers = Arrays.asList(BigDecimal.valueOf(1), BigDecimal.valueOf(4), BigDecimal.valueOf(5));
        BigDecimal result = this.additionService.performOperation(numbers);
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
    void shouldThrowExceptionWithEmptyList() {
        List<BigDecimal> numbers = List.of();
        assertThrows(InvalidOperationException.class, () -> this.additionService.performOperation(numbers));
    }

}
