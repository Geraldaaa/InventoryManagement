package com.inventar.InventoryManagement.service.impl;

import com.inventar.InventoryManagement.model.Delivery;
import com.inventar.InventoryManagement.repository.DeliveryRepository;
import com.inventar.InventoryManagement.service.DeliveryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository){
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    @Transactional
    public Delivery shtoDelivery(Delivery delivery){
        return deliveryRepository.save(delivery);
    }

    @Override
    @Transactional
    public Delivery updateDelivery(Long id, Delivery updatedDelivery){
        return deliveryRepository.findById(id).map(delivery -> {
            delivery.setDeliveryDate(updatedDelivery.getDeliveryDate());
            delivery.setOrders(updatedDelivery.getOrders());
            delivery.setTruck(updatedDelivery.getTruck());
            return deliveryRepository.save(delivery);
        }).orElseThrow(() -> new RuntimeException("Delivery not found"));
    }

    @Override
    @Transactional
    public void deleteDelivery(Long id){
        deliveryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Delivery> lexoDeliveries(){
        return deliveryRepository.findAll();
    }
}
