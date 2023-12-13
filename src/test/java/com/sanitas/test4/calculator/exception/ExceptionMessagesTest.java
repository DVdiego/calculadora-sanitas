package com.sanitas.test4.calculator.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ExceptionMessagesTest {

    private AutoCloseable closeable;

    @InjectMocks
    private ExceptionMessages exceptionMessages;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
        Map<String, String> messages = createTestMessages();
        this.exceptionMessages.setMessages(messages);
    }

    @AfterEach
    void releaseMocks() throws Exception {
        this.closeable.close();
    }

    @Test
    void getMessages() {
        Map<String, String> expectedMessages = createTestMessages();
        Map<String, String> actualMessages = this.exceptionMessages.getMessages();
        assertEquals(expectedMessages, actualMessages);
    }

    @Test
    void getOneMessage() {
        Map<String, String> actualMessages = exceptionMessages.getMessages();
        assertEquals("Invalid Operation", actualMessages.get("invalid-operation"));
    }

    private Map<String, String> createTestMessages() {
        Map<String, String> messages = new HashMap<>();
        messages.put("invalid-operation", "Invalid Operation");
        messages.put("incorrect-data-format", "Incorrect data format");
        messages.put("generic-exception", "Internal Server Error");
        return messages;
    }

}