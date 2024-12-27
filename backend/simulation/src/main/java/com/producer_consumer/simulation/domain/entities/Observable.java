package com.producer_consumer.simulation.domain.entities;

import com.producer_consumer.simulation.domain.entities.Observer;

public interface Observable {
    void addObserver(Observer observer);
    void notifyObservers();
}