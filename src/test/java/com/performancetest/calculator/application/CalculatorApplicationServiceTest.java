package com.performancetest.calculator.application;

import com.performancetest.calculator.context.TestTag;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Not recommended usually but it's ok in this instance since the dependency (Calculator) is static.
//I have used PER_CLASS lifecyle on occasion for only initializing external dependencies that are expensive
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag(TestTag.SERVICE_TEST)
public class CalculatorApplicationServiceTest {

    private CalculatorApplicationService calculatorApplicationService;

    @BeforeAll
    public void init() {
        calculatorApplicationService = new CalculatorApplicationService();
    }

    @Nested
    @DisplayName("BigDecimal add(BigDecimal augend, BigDecimal addend)")
    public class Add {

        @Test
        @DisplayName("passing in two big decimals - return sum")
        public void addTwoValuesReturnSum() {
            assertEquals(BigDecimal.valueOf(3), calculatorApplicationService.add(BigDecimal.valueOf(1), BigDecimal.valueOf(2)));
        }

    }

    @Nested
    @DisplayName("BigDecimal subtract(BigDecimal minuend, BigDecimal subtrahend)")
    public class Subtract {

        @Test
        @DisplayName("passing in two big decimals - return difference")
        public void subtractTwoValuesReturnSum() {
            assertEquals(BigDecimal.valueOf(-1), calculatorApplicationService.subtract(BigDecimal.valueOf(1), BigDecimal.valueOf(2)));
        }

    }

    @Nested
    @DisplayName("BigDecimal multiply(BigDecimal multiplicand, BigDecimal multiplier)")
    public class Multiply {

        @Test
        @DisplayName("passing in two big decimals - return product")
        public void subtractTwoValuesReturnSum() {
            assertEquals(BigDecimal.valueOf(6), calculatorApplicationService.multiply(BigDecimal.valueOf(3), BigDecimal.valueOf(2)));
        }

    }

    @Nested
    @DisplayName("BigDecimal divide(BigDecimal dividend, BigDecimal divisor)")
    public class Divide {

        @Test
        @DisplayName("passing in two big decimals - return dividend")
        public void subtractTwoValuesReturnSum() {
            assertEquals(BigDecimal.valueOf(3), calculatorApplicationService.divide(BigDecimal.valueOf(6), BigDecimal.valueOf(2)));
        }

    }
}
