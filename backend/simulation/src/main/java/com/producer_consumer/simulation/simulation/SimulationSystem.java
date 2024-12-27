package com.producer_consumer.simulation.simulation;

import com.producer_consumer.simulation.domain.entities.Machine;
import com.producer_consumer.simulation.domain.entities.Product;
import com.producer_consumer.simulation.domain.entities.ProductsQueue;
import com.producer_consumer.simulation.domain.snapshot.CareTaker;
import com.producer_consumer.simulation.domain.snapshot.Memento;

import java.util.ArrayList;
import java.util.List;

public class SimulationSystem {
    private static volatile SimulationSystem instance; // Volatile for thread-safe singleton
    private final List<Thread> threads;
    private final List<Machine> machines;
    private final List<ProductsQueue> queues;
    private final List<String> productColors;
    private final CareTaker careTaker;
    private int numberOfProducts;

    // Private constructor to enforce Singleton pattern
    private SimulationSystem() {
        this.threads = new ArrayList<>();
        this.machines = new ArrayList<>();
        this.queues = new ArrayList<>();
        this.productColors = new ArrayList<>();
        this.careTaker = CareTaker.getInstance();
    }

    // Double-checked locking for thread-safe Singleton instance
    public static SimulationSystem getInstance() {
        if (instance == null) {
            synchronized (SimulationSystem.class) {
                if (instance == null) {
                    instance = new SimulationSystem();
                }
            }
        }
        return instance;
    }

    // Getters and Setters
    public List<Machine> getMachines() {
        return new ArrayList<>(machines); // Return a copy to maintain encapsulation
    }

    public void setMachines(List<Machine> machines) {
        this.machines.clear();
        this.machines.addAll(machines);
    }

    public List<ProductsQueue> getQueues() {
        return new ArrayList<>(queues);
    }

    public void setQueues(List<ProductsQueue> queues) {
        this.queues.clear();
        this.queues.addAll(queues);
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
        initializeProducts(numberOfProducts);
    }

    // Initialize products and add them to the first queue
    private void initializeProducts(int numberOfProducts) {
        for (int i = 0; i < numberOfProducts; i++) {
            Product product = new Product();
            productColors.add(product.getColor());
            queues.get(0).addProduct(product);
        }
    }

    // Add a machine or queue
    public void addMachine(int id) {
        machines.add(new Machine(id));
    }

    public void addQueue(int id) {
        queues.add(new ProductsQueue(id));
    }

    // Retrieve machine or queue by ID
    private Machine getMachineById(int machineId) {
        return machines.stream()
                .filter(machine -> machine.getId() == machineId)
                .findFirst()
                .orElse(null);
    }

    private ProductsQueue getQueueById(int queueId) {
        return queues.stream()
                .filter(queue -> queue.getId() == queueId)
                .findFirst()
                .orElse(null);
    }

    // Connect machine and queue
    public void connectQueueToMachine(int queueId, int machineId) {
        ProductsQueue queue = getQueueById(queueId);
        Machine machine = getMachineById(machineId);
        if (queue != null && machine != null) {
            machine.addObserver(queue);
            queue.addObservableMachine(machine);
        }
    }

    public void connectMachineToQueue(int queueId, int machineId) {
        ProductsQueue queue = getQueueById(queueId);
        Machine machine = getMachineById(machineId);
        if (queue != null && machine != null) {
            machine.setSuccessorQueue(queue);
        }
    }

    // Clear simulation data
    public void clear() {
        threads.clear();
        machines.clear();
        queues.clear();
        productColors.clear();
        numberOfProducts = 0;
        careTaker.clearMementos();
    }

    // Prepare initial mementos
    public void makeMementosReady() {
        careTaker.clearMementos();
        Memento initialMemento = new Memento();
        machines.forEach(machine -> initialMemento.addColor("#808080"));
        queues.forEach(queue -> initialMemento.addQueueSize(0));
        if (!queues.isEmpty()) {
            initialMemento.getQueueSizes().set(0, numberOfProducts);
        }
        careTaker.addMemento(initialMemento);
    }

    // Reset simulation state
    public void reSimulate() {
        queues.get(queues.size() - 1).getProductsToProcess().clear();
        for (int i = 0; i < numberOfProducts; i++) {
            Product product = new Product(productColors.get(i));
            queues.get(0).getProductsToProcess().add(product);
        }
        makeMementosReady();
        threads.clear();
        simulate();
    }

    // Run the simulation
    public void simulate() {
        machines.forEach(machine -> {
            Thread thread = new Thread(machine);
            threads.add(thread);
        });
        threads.forEach(Thread::start);
    }
}
