package com.integrationtest.calculator.context.server;

public abstract class Server {

    private String alias;

    public Server(String alias) {
        this.alias = alias;
    }

    public abstract String getServerUri();

}
