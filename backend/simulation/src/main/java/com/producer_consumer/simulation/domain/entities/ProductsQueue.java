package com.producer_consumer.simulation.domain.entities;

import com.producer_consumer.simulation.domain.entities.Machine;
import com.producer_consumer.simulation.domain.entities.Observable;
import com.producer_consumer.simulation.domain.entities.Observer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ProductsQueue implements Observer {
    private final int id;
    private final ArrayList<Observable> observableMachines;
    private final Queue<Product> productsToProcess;

    public ProductsQueue(int id) {
        this.id = id;
        this.observableMachines = new ArrayList<>(5);
        this.productsToProcess = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Observable> getObservableMachines() {
        return new ArrayList<>(observableMachines);
    }

    public void addObservableMachine(Observable observableMachine) {
        this.observableMachines.add(observableMachine);
    }

    public Queue<Product> getProductsToProcess() {
        return new LinkedList<>(productsToProcess);
    }

    public int getNumberOfProducts() {
        return this.productsToProcess.size();
    }

    public synchronized Product sendNewProduct() {
        return this.productsToProcess.poll();
    }

    public void addProduct(Product product) {
        this.productsToProcess.add(product);
    }

    @Override
    public void update(Observable observable) {
        if (observable instanceof Machine) {
            ((Machine) observable).takeNewProduct();
        }
    }

    @Override
    public String toString() {
        return "ProductsQueue{" +
                "id=" + id +
                ", observableMachines=" + observableMachines +
                ", productsToProcess=" + productsToProcess +
                '}';
    }
}
