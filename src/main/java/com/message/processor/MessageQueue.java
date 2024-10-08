package com.message.processor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {

    private static final Logger logger = LogManager.getLogger(MessageQueue.class);

    private final BlockingQueue<String> queue;
    private final BlockingQueue<String> deadLetterQueue;

    public MessageQueue() {
        this.deadLetterQueue = new LinkedBlockingQueue<>();
        this.queue = new LinkedBlockingQueue<>();
    }

    // Add message to queue
    public boolean addMessage(String message) {
         return queue.offer(message);
    }

    // Add message to dead queue
    public boolean addDeadQueueMessage(String message) {
        return deadLetterQueue.offer(message);
    }

    // Get message from queue
    public String getMessage() throws InterruptedException {
        return queue.take();
    }

    // Get message from dead queue
    public String getDeadQueueMessage() throws InterruptedException {
        return queue.take();
    }

    // check is queue empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }

}
