package com.sanitas.test4.calculator.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnableConfigurationProperties(ExceptionMessages.class)
@TestPropertySource(properties = {
        "exception.messages.invalid-operation=Invalid operation test",
        "exception.messages.operation-not-supported=Operation not supported test",
        "exception.messages.generic-exception=Internal Server Error test",
        "exception.messages.incorrect-data-format=Incorrect data format test",
        "exception.messages.list-numbers-wrong=The list should contain at least two numbers test"
})
public class ExceptionMessagesTest {

    @Autowired
    private ExceptionMessages exceptionMessages;

    @Test
    void shouldLoadExceptionMessages() {
        Map<String, String> messages = this.exceptionMessages.getMessages();

        assertThat(messages).isNotEmpty();
        assertThat(messages.get("invalid-operation")).isEqualTo("Invalid operation test");
        assertThat(messages.get("operation-not-supported")).isEqualTo("Operation not supported test");
        assertThat(messages.get("generic-exception")).isEqualTo("Internal Server Error test");
        assertThat(messages.get("incorrect-data-format")).isEqualTo("Incorrect data format test");
        assertThat(messages.get("list-numbers-wrong")).isEqualTo("The list should contain at least two numbers test");
    }
}