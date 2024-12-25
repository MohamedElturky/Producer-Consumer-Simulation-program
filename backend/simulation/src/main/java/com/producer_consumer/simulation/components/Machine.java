package com.producer_consumer.simulation.components;

import java.util.Random;

public class Machine implements Runnable {
    private static final Random random = new Random();
    private final Queue inputQueue;
    private final Queue outputQueue;
    private final int id;

    public Machine(int id, Queue inputQueue, Queue outputQueue) {
        this.id = id;
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
    }

    @Override
    public void run() {
        try {
            while (inputQueue.getSize() > 0 || !Thread.currentThread().isInterrupted()) {
                Product product = inputQueue.removeProduct();
                processProduct(product);
                outputQueue.addProduct(product);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void processProduct(Product product) throws InterruptedException {
        System.out.println("Machine " + id + " processing product with color: " + product.getColor());
        Thread.sleep(random.nextInt(1000) + 500); // Simulate processing time
    }
}
