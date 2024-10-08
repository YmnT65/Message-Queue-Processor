package com.message.processor;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MessageProcessorTest {

    @Test
    public void testSuccessfulMessageProcessing() {
        MessageQueue messageQueue = new MessageQueue();
        MessageProcessor processor = new MessageProcessor(messageQueue);

        // Process not null message
        processor.processMessage("Test Message - 1");
        processor.processMessage("Test Message - 2");

        // Check success & error count
        assertEquals(2, processor.getSuccessCount());
        assertEquals(0, processor.getErrorCount());
    }

    @Test
    public void testErrorMessageProcessing() {
        MessageQueue messageQueue = new MessageQueue();
        MessageProcessor processor = new MessageProcessor(messageQueue);

        // Process null message
        processor.processMessage(null);
        processor.processMessage("");

        // Check success & error count
        assertEquals(0, processor.getSuccessCount());
        assertEquals(2, processor.getErrorCount());
    }
}

