package com.sanitas.test4.calculator.controller;

import com.sanitas.test4.calculator.OperationFactory;
import com.sanitas.test4.calculator.exception.InvalidOperationException;
import com.sanitas.test4.calculator.service.AdditionService;
import com.sanitas.test4.calculator.service.SubtractionService;
import io.corp.calculator.TracerAPI;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
@SpringBootTest
@AutoConfigureMockMvc
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OperationFactory operationFactory;

    @MockBean
    private TracerAPI tracerAPI;

    @InjectMocks
    private CalculatorController calculatorController;

    @Test
    void shouldPerformAdditionOperationSuccessfully() throws Exception {
        when(this.operationFactory.getOperation(any())).thenReturn(new AdditionService());

        this.mockMvc.perform(post("/calculator/perform")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"operation\": \"addition\", \"numbers\": [2, 3, 5]}")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(10)));
    }

    @Test
    void shouldPerformSubtractionOperationSuccessfully() throws Exception {
        when(this.operationFactory.getOperation(any())).thenReturn(new SubtractionService());

        this.mockMvc.perform(post("/calculator/perform")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"operation\": \"subtraction\", \"numbers\": [5, 3]}")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(2)));
    }
    @Test
    void shouldHandleInvalidOperation() throws Exception {
        when(this.operationFactory.getOperation(any())).thenThrow(new InvalidOperationException("Operation not supported"));

        this.mockMvc.perform(post("/calculator/perform")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"operation\": \"otherOperation\", \"numbers\": [2, 3]}")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", is("Operation not supported")));
    }

}