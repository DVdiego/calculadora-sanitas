package com.sanitas.test4.calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class CalculatorApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testMain(CapturedOutput output) {
		CalculatorApplication.main(new String[]{});
		assertThat(output).contains("Started CalculatorApplication");
	}
}
