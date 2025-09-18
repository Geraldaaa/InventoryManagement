package com.inventar.InventoryManagement.controller;


import com.inventar.InventoryManagement.model.Delivery;
import com.inventar.InventoryManagement.service.DeliveryService;
import lombok.Lombok;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public ResponseEntity<Delivery> addDelivery(@RequestBody Delivery delivery){
        return ResponseEntity.ok(deliveryService.shtoDelivery(delivery));
    }


    @GetMapping
    public ResponseEntity<List<Delivery>> readAllDeliveries(){
        return ResponseEntity.ok(deliveryService.lexoDeliveries());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Delivery> updateDelivery(@PathVariable Long id,@RequestBody Delivery updatedDelivery){
        return ResponseEntity.ok(deliveryService.updateDelivery(id,updatedDelivery));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteDelivery(@PathVariable Long id){
        deliveryService.deleteDelivery(id);
        return ResponseEntity.noContent().build();
    }





    public DeliveryService getDeliveryService() {
        return deliveryService;
    }

    public void setDeliveryService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }
}
