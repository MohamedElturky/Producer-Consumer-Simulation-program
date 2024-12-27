package com.producer_consumer.simulation.domain.snapshot;

import java.util.ArrayList;
import java.util.List;

public class Memento {
    private final List<String> machineColors; // Immutable references
    private final List<Integer> queueSizes;

    // Default constructor initializes empty lists
    public Memento() {
        this.machineColors = new ArrayList<>();
        this.queueSizes = new ArrayList<>();
    }

    // Constructor with predefined lists
    public Memento(List<String> machineColors, List<Integer> queueSizes) {
        this.machineColors = new ArrayList<>(machineColors); // Defensive copy
        this.queueSizes = new ArrayList<>(queueSizes);
    }

    // Add a machine color
    public void addColor(String color) {
        this.machineColors.add(color);
    }

    // Add a queue size
    public void addQueueSize(int size) {
        this.queueSizes.add(size);
    }

    // Getters return unmodifiable views for immutability
    public List<String> getMachineColors() {
        return new ArrayList<>(machineColors);
    }

    public List<Integer> getQueueSizes() {
        return new ArrayList<>(queueSizes);
    }

    // String representation for debugging
    @Override
    public String toString() {
        return "Memento{" +
                "machineColors=" + machineColors +
                ", queueSizes=" + queueSizes +
                '}';
    }
}
