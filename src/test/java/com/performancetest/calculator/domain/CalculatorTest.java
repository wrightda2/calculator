package com.performancetest.calculator.domain;

import com.performancetest.calculator.context.TestTag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag(TestTag.UNIT_TEST)
public class CalculatorTest {

    @Nested
    @DisplayName("BigDecimal add(BigDecimal augend, BigDecimal addend)")
    public class Add {

        @Test
        public void addTwoBigDecimalsReturnSum() {
            BigDecimal result = Calculator.add(BigDecimal.valueOf(1), BigDecimal.valueOf(2));
            assertEquals(BigDecimal.valueOf(3), result);
        }

    }

    @Nested
    @DisplayName("BigDecimal subtract(BigDecimal minuend, BigDecimal subtrahend)")
    public class Subtract {

        @Test
        public void addTwoBigDecimalsReturnSum() {
            BigDecimal result = Calculator.subtract(BigDecimal.valueOf(3), BigDecimal.valueOf(1));
            assertEquals(BigDecimal.valueOf(2), result);
        }

    }

    @Nested
    @DisplayName("BigDecimal multiply(BigDecimal multiplicand, BigDecimal multiplier)")
    public class Multiply {

        @Test
        public void addTwoBigDecimalsReturnSum() {
            BigDecimal result = Calculator.multiply(BigDecimal.valueOf(3), BigDecimal.valueOf(2));
            assertEquals(BigDecimal.valueOf(6), result);
        }

    }

    @Nested
    @DisplayName("BigDecimal divide(BigDecimal dividend, BigDecimal divisor)")
    public class Divide {

        @Test
        public void addTwoBigDecimalsReturnSum() {
            BigDecimal result = Calculator.divide(BigDecimal.valueOf(6), BigDecimal.valueOf(2));
            assertEquals(BigDecimal.valueOf(3), result);
        }

    }
}
