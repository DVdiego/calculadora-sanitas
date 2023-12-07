package com.sanitas.test4.calculator;

import com.sanitas.test4.calculator.exception.ExceptionMessages;
import com.sanitas.test4.calculator.model.ApiErrorResponse;
import com.sanitas.test4.calculator.model.BasicOperationRequest;
import com.sanitas.test4.calculator.model.OperationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculatorApplicationIntegrationTests {

	@LocalServerPort
	private int port;


	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ExceptionMessages exceptionMessages;

	@Test
	void testPerformAddition() {

		BasicOperationRequest request = new BasicOperationRequest();
		request.setNumbers(Arrays.asList(BigDecimal.valueOf(2), BigDecimal.valueOf(3)));
		request.setOperation("+");

		ResponseEntity<OperationResponse> responseEntity = this.restTemplate.postForEntity(
				"http://localhost:" + this.port + "/calculator/v1/perform",
				request,
				OperationResponse.class);

		BigDecimal result = Objects.requireNonNull(responseEntity.getBody()).getResult();
		String expression = String.join(" " + request.getOperation() + " ",
				request.getNumbers().stream().map(BigDecimal::toString).toArray(String[]::new));

		OperationResponse operationResponse = new OperationResponse();
		operationResponse.setExpression(expression);
		operationResponse.setResult(result);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody());
		assertEquals(operationResponse, responseEntity.getBody());
	}

	@Test
	void testPerformSubtraction() {

		BasicOperationRequest request = new BasicOperationRequest();
		request.setNumbers(Arrays.asList(BigDecimal.valueOf(5), BigDecimal.valueOf(2)));
		request.setOperation("-");

		ResponseEntity<OperationResponse> responseEntity = this.restTemplate.postForEntity(
				"http://localhost:" + this.port + "/calculator/v1/perform",
				request,
				OperationResponse.class);

		BigDecimal result = Objects.requireNonNull(responseEntity.getBody()).getResult();
		String expression = String.join(" " + request.getOperation() + " ",
				request.getNumbers().stream().map(BigDecimal::toString).toArray(String[]::new));

		OperationResponse operationResponse = new OperationResponse();
		operationResponse.setExpression(expression);
		operationResponse.setResult(result);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody());
		assertEquals(operationResponse, responseEntity.getBody());
	}

	@Test
	void testPerformNotSupportedOperation() {

		BasicOperationRequest request = new BasicOperationRequest();
		request.setNumbers(Arrays.asList(BigDecimal.valueOf(5), BigDecimal.valueOf(2)));
		request.setOperation("/");

		ResponseEntity<ApiErrorResponse> responseEntity = this.restTemplate.postForEntity(
				"http://localhost:" + this.port + "/calculator/v1/perform",
				request,
				ApiErrorResponse.class);

		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody());
		assertEquals(this.exceptionMessages.getMessages().get("invalid-operation"), responseEntity.getBody().getMessage());
		assertEquals(this.exceptionMessages.getMessages().get("operation-not-supported"), responseEntity.getBody().getDetails());
	}

}
