package com.performancetest.calculator.application;

import com.performancetest.calculator.domain.Calculator;
import com.performancetest.calculator.domain.Calculator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatorApplicationService {
    public BigDecimal add(BigDecimal augend, BigDecimal addend) {
        return Calculator.add(augend, addend);
    }

    public BigDecimal subtract(BigDecimal minuend, BigDecimal subtrahend) {
        return Calculator.subtract(minuend, subtrahend);
    }

    public BigDecimal multiply(BigDecimal multiplicand, BigDecimal multiplier) {
        return Calculator.multiply(multiplicand, multiplier);
    }

    public BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
        return Calculator.divide(dividend, divisor);
    }
}
