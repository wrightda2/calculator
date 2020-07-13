package com.performancetest.calculator;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.performancetest.calculator.context.TestTag;
import com.performancetest.calculator.presentation.rest.CalculatorController;
import com.performancetest.calculator.presentation.rest.CalculatorController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.RequestMapping;
import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Tag(TestTag.INTEGRATION_TEST)
@DisplayName("CalculatorController - /calculator")
class CalculatorApplicationTests {

	final private String basePath = CalculatorController.class.getAnnotation(RequestMapping.class).value()[0];

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Nested
	@DisplayName("/sum - GET")
	class Sum {

		final private String subPath = "/sum";

		@ParameterizedTest(name = "The sum of {0} and {1} - should return {2}")
		@CsvSource({
				"0, 3.1, 3.1",
				"3.1, 0, 3.1",
				"-3.1, 1.1, -2.0",
				"1.1, -3.1, -2.0",
				"-3.1, -1.1, -4.2"
		})
		void twoValidParamReturnsValidResults(BigDecimal augend, BigDecimal addend, BigDecimal expectedResult) throws Exception {
			String response = mockMvc.perform(get(basePath + subPath)
							.param("augend", augend.toString())
							.param("addend", addend.toString()))
							.andExpect(status().isOk())
							.andReturn().getResponse().getContentAsString();
			BigDecimal result = objectMapper.convertValue(response, BigDecimal.class);
			assertAll(
					() -> assertEquals(expectedResult, result)
			);
		}

		@Test
		@DisplayName("Request with null 'augend' param - return bad request")
		void nullAugendParamReturnBadRequest() throws Exception {
			mockMvc.perform(get(basePath + subPath)
					.param("addend", "1"))
					.andExpect(status().isBadRequest());
		}

		@Test
		@DisplayName("Request with null 'addend' param - return bad request")
		void nullAddendParamReturnBadRequest() throws Exception {
			mockMvc.perform(get(basePath + subPath)
					.param("augend", "1"))
					.andExpect(status().isBadRequest());
		}

	}

	@Nested
	@DisplayName("/difference - GET")
	class Difference {

		final private String subPath = "/difference";

		@ParameterizedTest(name = "The difference between {0} and {1}  - should return {2}")
		@CsvSource({
				"0, 3.1, -3.1",
				"3.1, 0, 3.1",
				"-3.1, 1.1, -4.2",
				"1.1, -3.1, 4.2",
				"-3.1, -1.1, -2.0"
		})
		void twoValidParamReturnsValidResults(BigDecimal minuend, BigDecimal subtrahend, BigDecimal expectedResult) throws Exception {
			String response = mockMvc.perform(get(basePath + subPath)
					.param("minuend", minuend.toString())
					.param("subtrahend", subtrahend.toString()))
					.andExpect(status().isOk())
					.andReturn().getResponse().getContentAsString();
			BigDecimal result = objectMapper.convertValue(response, BigDecimal.class);
			assertAll(
					() -> assertEquals(expectedResult, result)
			);
		}

		@Test
		@DisplayName("Request with null 'minuend' param - return bad request")
		void nullMinuendParamReturnBadRequest() throws Exception {
			mockMvc.perform(get(basePath + subPath)
					.param("subtrahend", "1"))
					.andExpect(status().isBadRequest());
		}

		@Test
		@DisplayName("Request with null 'subtrahend' param - return bad request")
		void nullAddendParamReturnBadRequest() throws Exception {
			mockMvc.perform(get(basePath + subPath)
					.param("minuend", "1"))
					.andExpect(status().isBadRequest());
		}
	}

	@Nested
	@DisplayName("/product - GET")
	class Product {

		final private String subPath = "/product";

		@ParameterizedTest(name = "The product of {0} and {1} should be {2}")
		@CsvSource({
				"0, 3.1, 0.0",
				"3.1, 0, 0.0",
				"-3.1, 2.1, -6.51",
				"2.1, -3.1, -6.51",
				"-3.1, -2.1, 6.51"
		})
		void twoValidParamReturnsValidResults(BigDecimal multiplicand, BigDecimal multiplier, BigDecimal expectedResult) throws Exception {
			String response = mockMvc.perform(get(basePath + subPath)
					.param("multiplicand", multiplicand.toString())
					.param("multiplier", multiplier.toString()))
					.andExpect(status().isOk())
					.andReturn().getResponse().getContentAsString();
			BigDecimal result = objectMapper.convertValue(response, BigDecimal.class);
			assertAll(
					() -> assertEquals(expectedResult, result)
			);
		}

		@Test
		@DisplayName("Request with null 'multiplicand' param - return bad request")
		void nullMultiplicParamReturnBadRequest() throws Exception {
			mockMvc.perform(get(basePath + subPath)
					.param("multiplier", "1"))
					.andExpect(status().isBadRequest());
		}

		@Test
		@DisplayName("Request with null 'subtrahend' param - return bad request")
		void nullMultiplierParamReturnBadRequest() throws Exception {
			mockMvc.perform(get(basePath + subPath)
					.param("multiplicand", "1"))
					.andExpect(status().isBadRequest());
		}

	}

	@Nested
	@DisplayName("/quotient - GET")
	class Quotient {

		final private String subPath = "/quotient";

		@ParameterizedTest(name = "The sum of {0} and {1} should be {2}")
		@CsvSource({
				"0.0, 3.0, 0",
				"-6.3, 2.1, -3",
				"6.3, -2.1, -3",
				"-6.3, -2.1, 3"
		})
		void twoValidParamReturnsValidResults(BigDecimal dividend, BigDecimal divisor, BigDecimal expectedResult) throws Exception {
			String response = mockMvc.perform(get(basePath + subPath)
					.param("dividend", dividend.toString())
					.param("divisor", divisor.toString()))
					.andExpect(status().isOk())
					.andReturn().getResponse().getContentAsString();
			BigDecimal result = objectMapper.convertValue(response, BigDecimal.class);
			assertAll(
					() -> assertEquals(expectedResult, result)
			);
		}

		@Test
		@DisplayName("Request with null 'dividend' param - return bad request")
		void nullDividendParamReturnBadRequest() throws Exception {
			mockMvc.perform(get(basePath + subPath)
					.param("divisor", "1"))
					.andExpect(status().isBadRequest());
		}

		@Test
		@DisplayName("Request with null 'divisor' param - return bad request")
		void nullDivisorParamReturnBadRequest() throws Exception {
			mockMvc.perform(get(basePath + subPath)
					.param("dividend", "1"))
					.andExpect(status().isBadRequest());
		}

		@Test
		@DisplayName("Request with 0 divisr - should return application exception")
		void dividorZeroShouldReturnGracefulException() throws Exception {
			MvcResult result = mockMvc.perform(get(basePath + subPath)
					.param("dividend", "1")
					.param("divisor", "0"))
					.andExpect(status().isBadRequest())
					.andReturn();

			assertEquals("Divisor param cannot be zero (0)", result.getResponse().getErrorMessage());
		}
	}

}
