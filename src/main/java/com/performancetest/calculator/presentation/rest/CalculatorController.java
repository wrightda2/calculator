package com.performancetest.calculator.presentation.rest;

import com.performancetest.calculator.application.CalculatorApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.BigInteger;

@RestController()
@RequestMapping("/calculator")
public class CalculatorController {

    private final CalculatorApplicationService calculatorApplicationService;

    public CalculatorController(CalculatorApplicationService calculatorApplicationService) {
        this.calculatorApplicationService = calculatorApplicationService;
    }

    @GetMapping("/sum")
    public BigDecimal sum(@RequestParam BigDecimal augend, @RequestParam BigDecimal addend) {
        return calculatorApplicationService.add(augend, addend);
    }

    @GetMapping("/difference")
    public BigDecimal difference(@RequestParam BigDecimal minuend, @RequestParam BigDecimal subtrahend) {
        return calculatorApplicationService.subtract(minuend, subtrahend);
    }

    @GetMapping("/product")
    public BigDecimal product(@RequestParam BigDecimal multiplicand, @RequestParam BigDecimal multiplier) {
        return calculatorApplicationService.multiply(multiplicand, multiplier);
    }

    @GetMapping("/quotient")
    public BigDecimal quotient(@RequestParam BigDecimal dividend, @RequestParam BigDecimal divisor) {
        if (divisor.toBigInteger() == BigInteger.ZERO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Divisor param cannot be zero (0)");
        }
        return calculatorApplicationService.divide(dividend, divisor);
    }
}
