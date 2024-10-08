package com.message.processor;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MessageProcessor {

    private static final Logger logger = LogManager.getLogger(MessageProcessor.class);

    private int successCount = 0;
    private int errorCount = 0;

    private final MessageQueue messageQueue;

    public MessageProcessor(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    // Process Message
    public void processMessage(String message) {
        try {

            if (message == null || message.isEmpty()) {
                throw new RuntimeException("Can not process null message.");
            }

            logger.info("Processed: {}", message);
            successCount++;
        } catch (RuntimeException e) {
            logger.error("RuntimeException while processing message", e);
            errorCount++;
        } catch (Exception e) {
            addToDeadLetterQueue(message);
            logger.error("Exception while processing message", e);
            errorCount++;
        }
    }

    // Add failed message to dead letter queue
    private void addToDeadLetterQueue(String message){
        boolean isMessageAdded = this.messageQueue.addDeadQueueMessage(message);
        if(!isMessageAdded){
            logger.error("Failed to add the message to the Dead Letter Queue. Message details: {}", message);
        }
    }

    public int getSuccessCount() {
        return successCount;
    }

    public int getErrorCount() {
        return errorCount;
    }
}
