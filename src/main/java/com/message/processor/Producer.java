package com.message.processor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Producer implements Runnable {

    private static final Logger logger = LogManager.getLogger(Producer.class);

    private final MessageQueue messageQueue;
    private final int messageCount;

    public Producer(MessageQueue messageQueue, int messageCount) {
        this.messageQueue = messageQueue;
        this.messageCount = messageCount;
    }

    @Override
    public void run() {
        for (int i = 1; i <= messageCount; i++) {
            String message = "Message " + i;
            if(Math.random() >= 0.8){
                message = "";
            }
            boolean isMessageAdded = messageQueue.addMessage(message);
            if(!isMessageAdded){
                logger.error("Produced failed to add message: ");
                throw new RuntimeException("Produced failed to add message");
            }
            logger.info("Produced: {}", message);
        }
    }
}
