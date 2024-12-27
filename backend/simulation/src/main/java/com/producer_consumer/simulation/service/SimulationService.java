package com.producer_consumer.simulation.service;

import com.producer_consumer.simulation.simulation.SimulationSystem;
import com.producer_consumer.simulation.domain.dto.SystemDto;
import com.producer_consumer.simulation.domain.snapshot.CareTaker;
import com.producer_consumer.simulation.domain.snapshot.Memento;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SimulationService {
    private final SimulationSystem simulationSystem;

    public SimulationService() {
        this.simulationSystem = SimulationSystem.getInstance();
    }

    public void addMachineToSystem(int id) {
        simulationSystem.addMachine(id);
    }

    public void addQueueToSystem(int id) {
        simulationSystem.addQueue(id);
    }

    public void addConnectionToSystem(SystemDto systemDto) {
        switch (systemDto.getSource().toLowerCase()) {
            case "machine" -> connectMachineToQueueInSystem(systemDto.getFromId(), systemDto.getToId());
            case "queue" -> connectQueueToMachineInSystem(systemDto.getFromId(), systemDto.getToId());
            default -> throw new IllegalArgumentException("Invalid source type: " + systemDto.getSource());
        }
    }

    private void connectQueueToMachineInSystem(int queueId, int machineId) {
        simulationSystem.connectQueueToMachine(queueId, machineId);
    }

    private void connectMachineToQueueInSystem(int machineId, int queueId) {
        simulationSystem.connectMachineToQueue(queueId, machineId);
    }

//    public ArrayList<Memento> sendingUpdates() {
//        return CareTaker.getInstance().getMementos();
//    }

    public void startSimulationSystem(int numberOfProducts) {
        setNumberOfProductsInSystem(numberOfProducts);
        simulationSystem.makeMementosReady();
        simulationSystem.simulate();
    }

    public void setNumberOfProductsInSystem(int numberOfProducts) {
        simulationSystem.setNumberOfProducts(numberOfProducts);
    }

    public void clearSystem() {
        simulationSystem.clear();
    }

    public void reSimulateSystem() {
        simulationSystem.reSimulate();
    }
}
