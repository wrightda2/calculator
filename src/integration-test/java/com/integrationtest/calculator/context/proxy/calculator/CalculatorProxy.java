package com.integrationtest.calculator.context.proxy.calculator;

import com.integrationtest.calculator.context.api.CalculatorClientBase;

import java.math.BigDecimal;

public class CalculatorProxy extends CalculatorClientBase {

    public CalculatorProxy() {
        super("/calculator");
    }

    public BigDecimal sum(BigDecimal augend, BigDecimal addend) {
        addPath("/sum");
        getMethod();
        if (augend != null) setQueryParameters("augend", augend.toString());
        if (addend != null) setQueryParameters("addend", addend.toString());
        return sendAndGet(BigDecimal.class);
    }

    public BigDecimal difference(BigDecimal minuend, BigDecimal subtrahend) {
        addPath("/difference");
        getMethod();
        if (minuend != null) setQueryParameters("minuend", minuend.toString());
        if (subtrahend != null) setQueryParameters("subtrahend", subtrahend.toString());
        return sendAndGet(BigDecimal.class);
    }

    public BigDecimal product(BigDecimal multiplicand, BigDecimal multiplier) {
        addPath("/product");
        getMethod();
        if (multiplicand != null) setQueryParameters("multiplicand", multiplicand.toString());
        if (multiplier != null) setQueryParameters("multiplier", multiplier.toString());
        return sendAndGet(BigDecimal.class);
    }

    public BigDecimal quotient(BigDecimal dividend, BigDecimal divisor) {
        addPath("/quotient");
        getMethod();
        if (dividend != null) setQueryParameters("dividend", dividend.toString());
        if (divisor != null) setQueryParameters("divisor", divisor.toString());
        return sendAndGet(BigDecimal.class);
    }
}
