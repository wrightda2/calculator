package com.integrationtest.calculator.acceptancetest;

import com.performancetest.calculator.context.TestProperties;
import com.performancetest.calculator.context.TestTag;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Tag(TestTag.SYSTEM_INTEGRATION_TEST)
public class CalculatorRestAssuredTest {

    @BeforeAll
    public static void setupRestAssuredContext() {
        baseURI = TestProperties.getAsString("base.url");
    }

    @Nested
    @DisplayName("Get - Sum")
    public class Sum {

        @Test
        public void withValidParameters() {
            with()
                .queryParam("augend", "1")
                .queryParam("addend", "2")
            .when()
                .get("/calculator/sum")
            .then()
                .statusCode(200)
                .body(is("3"));
        }

    }

    @Nested
    @DisplayName("Get - Difference")
    public class Difference {

        @Test
        public void withValidParameters() {
            with()
                .queryParam("minuend", "4")
                .queryParam("subtrahend", "1")
            .when()
                .get("/calculator/difference")
            .then()
                .statusCode(200)
                .body(is("3"));
        }

    }

    @Nested
    @DisplayName("Get - Product")
    public class Product {

        @Test
        public void withValidParameters() {
            with()
                .queryParam("multiplicand", "2")
                .queryParam("multiplier", "3")
            .when()
                .get("/calculator/product")
            .then()
                .statusCode(200)
                .body(is("6"));
        }

    }

    @Nested
    @DisplayName("Get - Quotient")
    public class Quotient {

        @Test
        public void withValidParameters() {
            with()
                    .queryParam("dividend", "6")
                    .queryParam("divisor", "3")
                    .when()
                    .get("/calculator/quotient")
                    .then()
                    .statusCode(200)
                    .body(is("2"));
        }

    }
}
