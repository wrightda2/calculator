package com.performancetest.calculator.presentation.rest;

import com.performancetest.calculator.application.CalculatorApplicationService;
import com.performancetest.calculator.context.TestTag;
import com.performancetest.calculator.presentation.rest.CalculatorController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CalculatorController.class)
@Tag(TestTag.CONTROLLER_TEST)
public class CalculatorControllerTest {

    final private String basePath = CalculatorController.class.getAnnotation(RequestMapping.class).value()[0];
    final private String subPath = "/sum";

    @MockBean
    private CalculatorApplicationService calculatorApplicationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Request with null 'augend' param - return bad request")
    void exampleControllerTest() throws Exception {
        when(calculatorApplicationService.add(any(), any())).thenReturn(BigDecimal.ONE);
        mockMvc.perform(get(basePath + subPath)
                .param("addend", "1")
                .param("augend", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

}
