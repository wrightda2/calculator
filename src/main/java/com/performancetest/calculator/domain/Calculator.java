package com.performancetest.calculator.domain;

import java.math.BigDecimal;

public class Calculator {

    public static BigDecimal add(BigDecimal augend, BigDecimal addend) {
        return augend.add(addend);
    }

    public static BigDecimal subtract(BigDecimal minuend, BigDecimal subtrahend) {
        return minuend.subtract(subtrahend);
    }

    public static BigDecimal multiply(BigDecimal multiplicand, BigDecimal multiplier) {
        return multiplicand.multiply(multiplier);
    }

    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
        return dividend.divide(divisor);
    }

    public static BigDecimal remainder(BigDecimal dividend, BigDecimal divisor) {
        return dividend.remainder(divisor);
    }
}
