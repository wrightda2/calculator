package com.integrationtest.calculator.context.server;

import com.performancetest.calculator.context.TestProperties;

public class CalculatorServer extends Server {

    private static String baseUrl = TestProperties.getAsString("base.url");

    public CalculatorServer() {
        super(baseUrl);
    }

    @Override
    public String getServerUri() {
        return baseUrl;
    }

}
