package com.inventar.InventoryManagement.controller;


import com.inventar.InventoryManagement.model.Truck;
import com.inventar.InventoryManagement.service.TruckService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trucks")
public class TruckController {

    private TruckService truckService;


    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }


    @PostMapping
    public void addTruck(Truck truck){
        truckService.shtoTruck(truck);
    }





    public TruckService getTruckService() {
        return truckService;
    }

    public void setTruckService(TruckService truckService) {
        this.truckService = truckService;
    }
}
