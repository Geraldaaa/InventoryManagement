package com.inventar.InventoryManagement.service;

import com.inventar.InventoryManagement.model.Delivery;

import java.util.List;

public interface DeliveryService {

    Delivery shtoDelivery(Delivery delivery);
    Delivery updateDelivery(Long id, Delivery updatedDelivery);
    void deleteDelivery(Long id);
    List<Delivery> lexoDeliveries();
}
