package com.message.processor;

public class Consumer implements Runnable {
    private final MessageQueue messageQueue;
    private final MessageProcessor messageProcessor;

    public Consumer(MessageQueue messageQueue, MessageProcessor messageProcessor) {
        this.messageQueue = messageQueue;
        this.messageProcessor = messageProcessor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = messageQueue.getMessage();
                messageProcessor.processMessage(message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
