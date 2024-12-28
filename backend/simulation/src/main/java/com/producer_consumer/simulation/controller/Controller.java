package com.producer_consumer.simulation.controller;

import com.producer_consumer.simulation.service.SimulationService;
import com.producer_consumer.simulation.domain.dto.SystemDto;
import com.producer_consumer.simulation.domain.snapshot.Memento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/")
@CrossOrigin("*")

public class Controller {

    @Autowired
    private SimulationService service;


    @PostMapping("/addMachine")
    public void addMachine(@RequestBody int id){
        System.out.println(id);
        service.addMachineToSystem(id);
    }

    @PostMapping("/addQueue")
    public void addQueue(@RequestBody int id){
        System.out.println(id);
        service.addQueueToSystem(id);
    }
    @PostMapping("/addConnection")
    public void addConnection(@RequestBody SystemDto systemDto){
        System.out.println(systemDto);
        service.addConnectionToSystem(systemDto);
    }

    @PostMapping("/run")
    public void runSimulation(@RequestBody int numberOfProducts){
        service.startSimulationSystem(numberOfProducts);
    }
    @GetMapping("/update")
    public ArrayList<Memento> sendUpdates(){
        return service.sendingUpdates();
    }

    @DeleteMapping("/clear")
    public void clear(){
        service.clearSystem();
    }

    @GetMapping("/replay")
    public void replaySimulation(){
        service.reSimulateSystem();
    }



}