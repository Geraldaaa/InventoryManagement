package com.inventar.InventoryManagement.service.impl;

import com.inventar.InventoryManagement.model.Truck;
import com.inventar.InventoryManagement.repository.TruckRepository;
import com.inventar.InventoryManagement.service.TruckService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TruckServiceImpl implements TruckService {

    private final TruckRepository truckRepository;

    public TruckServiceImpl(TruckRepository truckRepository){
        this.truckRepository = truckRepository;
    }

    @Override
    @Transactional
    public void shtoTruck(Truck truck){
        truckRepository.save(truck);
    }

    @Override
    @Transactional
    public Truck updateTruck(Long id, Truck updatedTruck){
        return truckRepository.findById(id).map(truck -> {
            truck.setChassisNumber(updatedTruck.getChassisNumber());
            truck.setDeliveries(updatedTruck.getDeliveries());
            truck.setContainerVolume(updatedTruck.getContainerVolume());
            truck.setLicensePlate(updatedTruck.getLicensePlate());
            return truckRepository.save(truck);
        }).orElseThrow(() -> new RuntimeException("Truck not found"));
    }

    @Override
    @Transactional
    public List<Truck> lexoTrucks(){
        return truckRepository.findAll();
    }

    @Override
    @Transactional
    public void fshiTruck(Long id){
        truckRepository.deleteById(id);
    }
}
