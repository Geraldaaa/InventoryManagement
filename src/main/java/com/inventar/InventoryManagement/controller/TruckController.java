package com.inventar.InventoryManagement.controller;


import com.inventar.InventoryManagement.model.Truck;
import com.inventar.InventoryManagement.service.TruckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trucks")
public class TruckController {

    private TruckService truckService;


    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }


    @PostMapping
    public ResponseEntity<Truck> addTruck( @RequestBody Truck truck){
        Truck t = truckService.shtoTruck(truck);
        return ResponseEntity.status(HttpStatus.CREATED).body(t);
    }


    @GetMapping
    public ResponseEntity<List<Truck>> readTrucks(){
        return ResponseEntity.ok(truckService.lexoTrucks());
    }



    @PutMapping("/{id}")
    public ResponseEntity<Truck> updateTruck(@PathVariable Long id,@RequestBody Truck updatedTruck){
        return ResponseEntity.ok(truckService.updateTruck(id,updatedTruck));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTruck(@PathVariable Long id){
        truckService.fshiTruck(id);
        return ResponseEntity.noContent().build();
    }





    public TruckService getTruckService() {
        return truckService;
    }


    public void setTruckService(TruckService truckService) {
        this.truckService = truckService;
    }
}
