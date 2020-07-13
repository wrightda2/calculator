package com.integrationtest.calculator.context;

import com.integrationtest.calculator.context.proxy.calculator.CalculatorProxy;

public class RestCalls {

    public static CalculatorProxy calculator() {
        return new CalculatorProxy();
    }
}
