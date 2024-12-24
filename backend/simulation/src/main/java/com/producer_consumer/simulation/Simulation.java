package com.producer_consumer.simulation;

import com.producer_consumer.simulation.components.Machine;
import com.producer_consumer.simulation.components.Queue;
import com.producer_consumer.simulation.components.Product;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Machine> machines = new ArrayList<>();
    private final List<Thread> machineThreads = new ArrayList<>();
    private final Queue[] queues;

    public Simulation(int numMachines, int numQueues) {
        queues = new Queue[numQueues];
        for (int i = 0; i < numQueues; i++) {
            queues[i] = new Queue();
        }

        for (int i = 0; i < numMachines; i++) {
            Machine machine = new Machine(i, queues[i % numQueues], queues[(i + 1) % numQueues]);
            machines.add(machine);
            machineThreads.add(new Thread(machine));
        }
    }

    public void start() {
        for (Thread thread : machineThreads) {
            thread.start();
        }
    }

    public void stop() {
        for (Thread thread : machineThreads) {
            thread.interrupt();
        }
    }

    public Queue getQueue(int index) {
        return queues[index];
    }
}
