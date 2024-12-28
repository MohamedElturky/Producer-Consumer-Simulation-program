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

    // Ensure the capacity and initialize default values for machineColors
    public void ensureMachineColorsCapacity(int index, String defaultColor) {
        while (machineColors.size() <= index) {
            machineColors.add(defaultColor);
        }
    }

    // Ensure the capacity and initialize default values for queueSizes
    public void ensureQueueSizesCapacity(int index, int defaultSize) {
        while (queueSizes.size() <= index) {
            queueSizes.add(defaultSize);
        }
    }

    // Add a machine color
    public void addColor(String color) {
        this.machineColors.add(color);
    }

    // Add a queue size
    public void addQueueSize(int size) {
        this.queueSizes.add(size);
    }

    // Getters return the lists directly
    public List<String> getMachineColors() {
        return machineColors;
    }

    public List<Integer> getQueueSizes() {
        return queueSizes;
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
