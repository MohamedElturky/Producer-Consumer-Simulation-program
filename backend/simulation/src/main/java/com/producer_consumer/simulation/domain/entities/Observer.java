package com.producer_consumer.simulation.domain.entities;

import com.producer_consumer.simulation.domain.entities.Observable;

public interface Observer {
    void update(Observable observable);
}