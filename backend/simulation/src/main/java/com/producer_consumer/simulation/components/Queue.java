package com.producer_consumer.simulation.components;

import java.util.LinkedList;

public class Queue {
    private final LinkedList<Product> products = new LinkedList<>();

    public synchronized void addProduct(Product product) {
        products.add(product);
        notifyAll();
    }

    public synchronized Product removeProduct() throws InterruptedException {
        while (products.isEmpty()) {
            wait();
        }
        return products.removeFirst();
    }

    public synchronized int getSize() {
        return products.size();
    }
}
