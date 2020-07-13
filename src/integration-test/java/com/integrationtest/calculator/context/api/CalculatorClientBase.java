package com.integrationtest.calculator.context.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.io.CharStreams;
import com.integrationtest.calculator.context.server.CalculatorServer;
import com.performancetest.calculator.context.TestProperties;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class CalculatorClientBase extends HttpClientBase {

    private static RestTemplate restTemplate;
    private static CloseableHttpClient httpClient;

    public CalculatorClientBase(ObjectMapper objectMapper, String... path) {
        super(new CalculatorServer(), objectMapper);
        addPath(path);
    }

    public CalculatorClientBase(String... path) {
        this();
        addPath(path);
    }

    public CalculatorClientBase() {
        this(new ObjectMapper()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
    }

    public void addPath(String... paths) {
        super.addPath(paths);
    }

    public void setQueryParameters(MultiValueMap<String, String> queryParameters) {
        super.setQueryParameters(queryParameters);
    }

    public void setMessage(Object message) {
        super.setMessage(message);
    }

    /**
     * Update project specific headers, for example, Authentication Token, Session cookies, Media Type.
     *
     * @param headers the headers to update
     */
    @Override
    protected void updateHeaders(HttpHeaders headers) {

    }

    private CloseableHttpClient getHttpClient(){
        if (httpClient == null) {
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

            // test.properties contains the proxy information
            if (TestProperties.getAsBool("proxy.enabled")) {
                httpClientBuilder.setProxy(
                        new HttpHost(TestProperties.getAsString("proxy.host"),
                                TestProperties.getAsInt("proxy.port"), "http"));
            }
            httpClient = httpClientBuilder.build();
        }

        return httpClient;
    }

    /**
     * <p>Create a customized RestTemplate that sets proxy information and overrides the default error handler</p>
     *
     * @return Customized RestTemplate
     */
    private RestTemplate restTemplate() {
        if (restTemplate == null) {
            restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(getHttpClient()));

            // Override the default error handler to throw customized WebException
            restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
                @Override
                public void handleError(ClientHttpResponse clienthttpresponse) throws IOException {
                    if (clienthttpresponse.getStatusCode() != HttpStatus.OK) {
                        Reader reader = new InputStreamReader(clienthttpresponse.getBody());
                        throw new WebException(CharStreams.toString(reader), clienthttpresponse.getStatusCode(),
                                clienthttpresponse.getHeaders());
                    }
                }
            });
        }

        return restTemplate;
    }

    @Override
    public ResponseEntity<String> send() {
        this.updateHeaders(this.headers);
        ResponseEntity<String> entity = restTemplate().exchange(this.getUrl(), this.method, new HttpEntity<>(this.message, this.getHeaders()), String.class);
        return entity;
    }

    /**
     * Sends the request to configured URI, and returns the response as a {@link ResponseEntity}, typed with
     * the given class.
     *
     * @param clazz the type of the return value
     * @return the response as an entity
     */
    public <T> ResponseEntity<T> send(Class<T> clazz) {
        updateHeaders(headers);
        return restTemplate().exchange(getUrl(), method,
                new HttpEntity<>(message, getHeaders()), clazz);
    }

    /**
     * Sends the request to configured URI, and returns the response as a {@link ResponseEntity}. The given
     * {@link ParameterizedTypeReference} is used to pass generic type information for the response.
     *
     * @param responseType the type of the return value
     * @return the response as entity
     * @since 3.2
     */
    public <T> ResponseEntity<T> send(ParameterizedTypeReference<T> responseType) {
        updateHeaders(headers);
        return restTemplate().exchange(getUrl(), method,
                new HttpEntity<>(message, getHeaders()), responseType);
    }

    public void setAuthorizationHeader(String tenant) {
        getHeaders().add("Tenant-Alias", tenant);
        getHeaders().add("Authorization", TestProperties.getAsString("base.auth.header"));
    }

    public void setAcceptLanguageHeader(String language) {
        getHeaders().add("Accept-Language", language);
    }

    // Set HttpMethod

    public HttpClientBase getMethod() {
        setMethod(HttpMethod.GET);
        return this;
    }

    public HttpClientBase postMethod() {
        setMethod(HttpMethod.POST);
        return this;
    }

    public HttpClientBase putMethod() {
        setMethod(HttpMethod.PUT);
        return this;
    }

    public HttpClientBase deleteMethod() {
        setMethod(HttpMethod.DELETE);
        return this;
    }

    public HttpClientBase patchMethod() {
        setMethod(HttpMethod.PATCH);
        return this;
    }
}
