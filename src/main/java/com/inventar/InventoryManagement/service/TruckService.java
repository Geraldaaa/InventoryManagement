package com.inventar.InventoryManagement.service;

import com.inventar.InventoryManagement.model.Truck;

import java.util.List;

public interface TruckService {

    Truck shtoTruck(Truck truck);

    Truck updateTruck(Long id, Truck updatedTruck);

    List<Truck> lexoTrucks();

    void fshiTruck(Long id);
}
