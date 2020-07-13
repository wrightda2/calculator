package com.integrationtest.calculator.context.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class WebException extends RuntimeException{
    private HttpStatus status;
    private HttpHeaders headers;

    public WebException(String body, HttpStatus statusCode, HttpHeaders headers) {
        super(body);
        this.status = statusCode;
        this.headers = headers;
    }

    public HttpStatus getStatus() { return status; }

    public HttpHeaders getHeaders() {
        return headers;
    }

}
