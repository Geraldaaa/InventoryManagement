package com.inventar.InventoryManagement.service;

import com.inventar.InventoryManagement.model.Delivery;
import com.inventar.InventoryManagement.repository.DeliveryRepository;

public class DeliveryService {

    private DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository){

        this.deliveryRepository = deliveryRepository;

    }

    public void shtoDelivery(Delivery delivery){
        deliveryRepository.save(delivery);
    }

    public Delivery updateDelivery(Long id, Delivery upatedDelivery){

        return deliveryRepository.findById(id).map(delivery -> {

            delivery.setDeliveryDate(upatedDelivery.getDeliveryDate());
            delivery.setOrders(upatedDelivery.getOrders());
            delivery.setTruck(upatedDelivery.getTruck());

            return deliveryRepository.save(delivery);

        }).orElseThrow(() -> new RuntimeException("Delivery not found"));
    }
}
