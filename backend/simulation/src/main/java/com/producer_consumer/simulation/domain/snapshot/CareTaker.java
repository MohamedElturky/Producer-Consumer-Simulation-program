package com.producer_consumer.simulation.domain.snapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CareTaker {
    private final List<Memento> mementos; // Use List interface for flexibility
    private static CareTaker instance;   // Singleton instance

    // Private constructor to enforce Singleton pattern
    private CareTaker() {
        this.mementos = Collections.synchronizedList(new ArrayList<>()); // Thread-safe list
    }

    // Static method to access the Singleton instance
    public static CareTaker getInstance() {
        if (instance == null) {
            synchronized (CareTaker.class) { // Double-checked locking
                if (instance == null) {
                    instance = new CareTaker();
                }
            }
        }
        return instance;
    }

    // Retrieve the list of mementos
    public List<Memento> getMementos() {
        return new ArrayList<>(mementos); // Return a copy to maintain encapsulation
    }

    // Add a memento to the list
    public void addMemento(Memento memento) {
        mementos.add(memento);
    }

    // Clone the last memento
    public synchronized Memento cloneLastMemento() {
        if (mementos.isEmpty()) {
            throw new IllegalStateException("No mementos available to clone.");
        }
        Memento lastMemento = mementos.get(mementos.size() - 1);
        return new Memento(
                new ArrayList<>(lastMemento.getMachineColors()),
                new ArrayList<>(lastMemento.getQueueSizes())
        );
    }

    // Clear all stored mementos
    public void clearMementos() {
        mementos.clear();
    }
}
