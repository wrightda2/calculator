package com.integrationtest.calculator.acceptancetest;

import com.integrationtest.calculator.context.RestCalls;
import com.integrationtest.calculator.context.api.WebException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorProxyBuilderTest {

    @Nested
    public class Sum {

        @Test
        public void canSumTwoValidBigDecimals() {
            BigDecimal response = RestCalls.calculator().sum(BigDecimal.valueOf(1), BigDecimal.valueOf(3));
            assertEquals(BigDecimal.valueOf(4), response);
        }

        @Test
        public void nullParameterReturnsExceptionReturnsResults() {
            WebException exception = assertThrows(WebException.class,
                    () -> RestCalls.calculator().sum(BigDecimal.valueOf(1), null));
            assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
            assertTrue(exception.getMessage().contains("Bad Request"));
        }

    }

    @Nested
    public class Difference {

        @Test
        public void canSubtractTwoValidBigDecimalsReturnsResults() {
            BigDecimal response = RestCalls.calculator().difference(BigDecimal.valueOf(1), BigDecimal.valueOf(3));
            assertEquals(BigDecimal.valueOf(-2), response);
        }

    }

    @Nested
    public class Product {

        @Test
        public void canMulitpleTwoValidNumbersReturnsResults() {
            BigDecimal response = RestCalls.calculator().product(BigDecimal.valueOf(3), BigDecimal.valueOf(2));
            assertEquals(BigDecimal.valueOf(6), response);
        }

    }

    @Nested
    public class Quotient {

        @Test
        public void canDivideTwoValidBigDecimalsReturnsResults() {
            BigDecimal response = RestCalls.calculator().quotient(BigDecimal.valueOf(6), BigDecimal.valueOf(3));
            assertEquals(BigDecimal.valueOf(2), response);
        }

        @Test
        public void divisorZeroThrowsException() {
            WebException exception = assertThrows(WebException.class,
                    () -> RestCalls.calculator().quotient(BigDecimal.valueOf(1), BigDecimal.ZERO));
            assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
            assertTrue(exception.getMessage().contains("Bad Request"));
        }

    }
}
