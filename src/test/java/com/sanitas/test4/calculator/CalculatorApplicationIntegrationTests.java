package com.sanitas.test4.calculator;

import com.sanitas.test4.calculator.model.BasicOperationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculatorApplicationIntegrationTests {

	@LocalServerPort
	private int port;


	@Autowired
	private TestRestTemplate restTemplate;


	@Test
	void testPerformAddition() {

		BasicOperationRequest request = new BasicOperationRequest();
		request.setNumbers(Arrays.asList(BigDecimal.valueOf(2), BigDecimal.valueOf(3)));
		request.setOperation("+");

		ResponseEntity<BigDecimal> responseEntity = this.restTemplate.postForEntity(
				"http://localhost:" + this.port + "/calculator/v1/perform",
				request,
				BigDecimal.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody());
		assertEquals(BigDecimal.valueOf(5), responseEntity.getBody());
	}

	@Test
	void testPerformSubtraction() {

		BasicOperationRequest request = new BasicOperationRequest();
		request.setNumbers(Arrays.asList(BigDecimal.valueOf(5), BigDecimal.valueOf(2)));
		request.setOperation("-");

		ResponseEntity<BigDecimal> responseEntity = this.restTemplate.postForEntity(
				"http://localhost:" + this.port + "/calculator/v1/perform",
				request,
				BigDecimal.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody());
		assertEquals(BigDecimal.valueOf(3), responseEntity.getBody());
	}

}
