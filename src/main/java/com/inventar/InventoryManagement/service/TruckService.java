package com.inventar.InventoryManagement.service;


import com.inventar.InventoryManagement.model.Truck;
import com.inventar.InventoryManagement.repository.TruckRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TruckService {

    private TruckRepository truckRepository;

    public TruckService(TruckRepository truckRepository){
        this.truckRepository = truckRepository;
    }

    @Transactional
    public void shtoTruck(Truck truck){
        truckRepository.save(truck);
    }

    @Transactional
    public Truck updateTruck(Long id, Truck updatedTruck){
        return truckRepository.findById(id).map(truck -> {

            truck.setChassisNumber(updatedTruck.getChassisNumber());
            truck.setDeliveries(updatedTruck.getDeliveries());
            truck.setContainerVolume(updatedTruck.getContainerVolume());
            truck.setLicensePlate(updatedTruck.getLicensePlate());

            return truckRepository.save(truck);

        }).orElseThrow();
    }

    @Transactional
    public List<Truck> lexoTrucks(){
        return truckRepository.findAll();
    }

    @Transactional
    public void fshiTruck(Long id){
        truckRepository.deleteById(id);
    }
}
