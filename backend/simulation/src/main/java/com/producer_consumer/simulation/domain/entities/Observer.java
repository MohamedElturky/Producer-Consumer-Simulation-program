package com.producer_consumer.simulation.domain.entities;

import com.example.Producer_Consumer_Simulation.Machines.Observable;

public interface Observer {
    void update(Observable observable);
}