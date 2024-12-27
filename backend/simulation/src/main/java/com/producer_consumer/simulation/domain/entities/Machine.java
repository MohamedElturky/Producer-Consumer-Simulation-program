package com.producer_consumer.simulation.domain.entities;

import com.producer_consumer.simulation.domain.snapshot.CareTaker;
import com.producer_consumer.simulation.domain.snapshot.Memento;
import com.producer_consumer.simulation.simulation.SimulationSystem;
import com.producer_consumer.simulation.domain.entities.Product;
import com.producer_consumer.simulation.domain.entities.ProductsQueue;

import java.util.ArrayList;
import java.util.Random;

public class Machine implements Observable, Runnable {
    private final int id;
    private final int processingRate;
    private final ArrayList<Observer> observers;
    private boolean isReady;
    private ProductsQueue successorQueue;
    private Product inOutProduct;
    private static CareTaker careTaker;
    private ProductsQueue lastSupplier;

    public Machine(int id) {
        this.id = id;
        this.processingRate = generateProcessingRate(3000, 10000);
        this.observers = new ArrayList<>(5);
        CareTaker careTaker = CareTaker.getInstance();
        this.isReady = true;

        System.out.println("Machine: " + this.id + " takes time " + this.processingRate + " ms to process.");
    }

    // Generate random processing rate within a range
    private int generateProcessingRate(int minRate, int maxRate) {
        return new Random().nextInt(maxRate - minRate + 1) + minRate;
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.update(this);
            if (inOutProduct != null) {
                this.lastSupplier = (ProductsQueue) observer;
                return;
            }
        }
    }

    public int getId() {
        return id;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public ProductsQueue getSuccessorQueue() {
        return successorQueue;
    }

    public void setSuccessorQueue(ProductsQueue successorQueue) {
        this.successorQueue = successorQueue;
    }

    public synchronized void takeNewProduct() {
        for (Observer observer : observers) {
            ProductsQueue queue = (ProductsQueue) observer;
            if (!queue.getProductsToProcess().isEmpty()) {
                inOutProduct = queue.sendNewProduct();
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Machine{" +
                "id=" + id +
                ", processingRate=" + processingRate +
                ", isReady=" + isReady +
                ", successorQueue=" + (successorQueue != null ? successorQueue.getId() : "null") +
                ", inOutProduct=" + inOutProduct +
                '}';
    }

    @Override
    public void run() {
        while (true) {
            try {
                Memento machineChanges = null;

                notifyObservers();

                if (inOutProduct != null) {
                    synchronized (careTaker.getMementos()) {
                        machineChanges = careTaker.cloneLastMemento();
                        updateMementoForProcessingStart(machineChanges);
                        careTaker.addMemento(machineChanges);
                    }
                }

                if (inOutProduct != null) {
                    processProduct(machineChanges);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (isSimulationComplete()) {
                System.out.println("Finished processing in thread: " + Thread.currentThread());
                break;
            }
        }
    }

    private void updateMementoForProcessingStart(Memento memento) {
        memento.getMachineColors().set(this.id, inOutProduct.getColor());
        memento.getQueueSizes().set(this.lastSupplier.getId(), this.lastSupplier.getNumberOfProducts());
    }

    private void processProduct(Memento machineChanges) throws InterruptedException {
        isReady = false;
        System.out.println(Thread.currentThread() + " created Memento before sleep: " + machineChanges);

        Thread.sleep(processingRate);

        machineChanges = careTaker.cloneLastMemento();
        machineChanges.getMachineColors().set(this.id, "#808080");
        machineChanges.getQueueSizes().set(this.successorQueue.getId(), this.successorQueue.getNumberOfProducts() + 1);

        System.out.println(Thread.currentThread() + " created Memento at the end of processing: " + machineChanges);
        careTaker.addMemento(machineChanges);

        this.successorQueue.addProduct(inOutProduct);
        inOutProduct = null;
        isReady = true;
    }

    private boolean isSimulationComplete() {
        SimulationSystem system = SimulationSystem.getInstance();
        ProductsQueue lastQueue = system.getQueues().get(system.getQueues().size() - 1);
        return lastQueue.getProductsToProcess().size() == system.getNumberOfProducts();
    }
}
