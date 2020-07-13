package com.integrationtest.calculator.context.api;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.integrationtest.calculator.context.server.Server;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class HttpClientBase {

    protected final Server server;
    protected final RestTemplate restTemplate;
    protected final ObjectMapper objectMapper;

    protected HttpMethod method;
    protected HttpHeaders headers = new HttpHeaders();
    protected Object message;
    protected HttpEntity<Object> payload;
    protected MultiValueMap<String, String> queryParameters;
    protected String path;

    /**
     * This class uses ObjectMapper to deserialize the message into JSON object<br>
     * This class uses RestTemplate to send and receive messages
     *
     * @param server
     */
    protected HttpClientBase(Server server) {
        this(server, new ObjectMapper(), new RestTemplate());
    }

    /**
     * This class uses ObjectMapper to deserialize the message into JSON object<br>
     * This class uses RestTemplate to send and receive messages<br>
     * Use this constructor to overrides the default <b>ObjectMapper</b>
     *
     * @param server
     * @param objectMapper
     */
    protected HttpClientBase(Server server, ObjectMapper objectMapper) {
        this(server, objectMapper, new RestTemplate());
    }

    /**
     * This class uses ObjectMapper to deserialize the message into JSON object
     * This class uses RestTemplate to send and receive messages
     * Use this constructor to overrides the default <b>RestTemplate</b>
     *
     * @param server
     * @param restTemplate
     */
    protected HttpClientBase(Server server, RestTemplate restTemplate) {
        this(server, new ObjectMapper(), restTemplate);
    }

    /**
     * This class uses ObjectMapper to deserialize the message into JSON object<br>
     * This class uses RestTemplate to send and receive messages<br>
     * Use this constructor to override both the default <b>ObjectMapper</b> and <b>RestTemplate</b>
     *
     * @param server
     * @param objectMapper
     * @param restTemplate
     */
    protected HttpClientBase(Server server, ObjectMapper objectMapper, RestTemplate restTemplate) {
        this.server = server;
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    /**
     * Allows extending class to update the header used by this class
     *
     * @param headers
     */
    protected abstract void updateHeaders(HttpHeaders headers);

    /**
     * Server URI is defined by the server object passed in constructor
     *
     * @return URI defined by the Server
     */
    protected String getBaseServerURI() { return server.getServerUri(); }

    /**
     * Sets the message to be sent to the server
     *
     * @param message
     */
    protected void setMessage(Object message) {
        this.message = message;
    }

    /**
     * Sets the API path. ie. http://localhost/<b>path</b>
     *
     * @param paths
     */
    protected void addPath(String... paths) {
        if (this.path == null)
            this.path = "";
        for (String path : paths)
            this.path = this.path.concat(path);
    }

    /**
     * Adds one or more query parameter(s) to the URI<br>
     * ie. http://localhost/path<b>?paramKey1=paramValue1&paramKey2=paramValue2</b>
     *
     * @param key
     * @param value
     */
    protected void setQueryParameters(String key, String value) {
        if (queryParameters == null) {
            queryParameters = new LinkedMultiValueMap<>();
        }
        queryParameters.add(key, value);
    }

    /**
     * Sets a query parameter to be sent to server
     *
     * @param queryParameters
     */
    protected void setQueryParameters(MultiValueMap<String, String> queryParameters) {
        this.queryParameters = queryParameters;
    }

    /**
     * Sets the HttpMethod
     *
     * @param method Set HttpMethod
     */
    protected void setMethod(HttpMethod method) {
        this.method = method;
    }

    /**
     * Gets the HttpMethod
     */
    protected HttpMethod getHttpMethod() {
        return method;
    }


    /* send methods */

    /**
     * Sends the Request
     *
     * @return Unformatted response as ResponseEntity&lt;String&gt;
     */
    protected ResponseEntity<String> send() {
        updateHeaders(headers);
        ResponseEntity<String> entity =
                restTemplate.exchange(getUrl(), method, new HttpEntity<>(message, getHeaders()), String.class);
        return entity;
    }

    /**
     * Sends the Request and returns the response as an instance of T
     *
     * @param <T> class representing the returned object
     * @return message formatted as an instance of T
     */
    protected <T> T sendAndGet(Class<T> clazz) {
        try {
            return objectMapper.readValue(send().getBody(), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sends the Request and returns the response as an instance of T
     *
     * @param <T> class representing the returned object
     * @return message formatted as an instance of T
     */
    protected <T> T sendAndGet(TypeReference type) {
        try {
            return (T) objectMapper.readValue(send().getBody(), type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sends the Request and returns the response as List&lt;T&gt;
     *
     * @param <T> class representing the returned object
     * @return message formatted as List&lt;T&gt;
     */
    protected <T> List<T> sendAndGetAsList(Class<T> clazz) {
        T[] response = (T[])sendAndGet(((T[]) Array.newInstance(clazz, 1)).getClass());
        return Arrays.asList(response);
    }

    /**
     * Sends the Request and returns the response as a String
     *
     * @return message formatted as a String
     */
    protected String sendAndGetAsString() {
        return send().getBody();
    }

    /**
     * Sends the Request and returns the response as a boolean
     *
     * @return message formatted as a boolean
     */
    protected boolean sendAndGetAsBoolean() {
        return Boolean.parseBoolean(send().getBody());
    }

    /**
     * Sends the Request and returns the response as an Integer
     *
     * @return message formatted as an Integer
     */
    protected int sendAndGetAsInt() {
        return Integer.parseInt(send().getBody());
    }

    /* Private Methods */

    /**
     * Creates the URI
     *
     * @return  URI
     */
    protected URI getUrl() {
        // TODO: Need to fix if path = null
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getBaseServerURI() + path);
        builder.queryParams(queryParameters);
        return builder.build().encode().toUri();
    }

    /**
     * Creates the headers<br>
     * Allows subclass to update and override the header set by this class
     *
     * @return HttpHeaders
     */
    protected HttpHeaders getHeaders() {
        return headers;
    }

    protected HttpClientBase setBooleanParameter(String key, Boolean value) {
        setQueryParameters(key, value.toString());
        return this;
    }

    protected HttpClientBase setLongParameter(String key, Long value) {
        setQueryParameters(key, (value != null) ? value.toString() : "");
        return this;
    }

    protected HttpClientBase setListParameters(String key, List<String> items) {
        if (items == null || items.size() == 0) {
            setQueryParameters(key, "");
            return this;
        }
        setQueryParameters(key, String.join(",", items));
        return this;
    }

    protected HttpClientBase setListParameters(String key, Long[] items) {
        if (items ==  null || items.length == 0) {
            setQueryParameters(key, "");
            return this;
        }

        String value = Arrays.stream(items)
                .map(Object::toString)
                .collect(Collectors.joining(","));
        setQueryParameters(key, value);
        return this;
    }
}
