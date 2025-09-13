package com.inventar.InventoryManagement.service;


import com.inventar.InventoryManagement.model.OrderItem;
import com.inventar.InventoryManagement.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository){
        this.orderItemRepository = orderItemRepository;
    }

    public void shtoOrderItem(OrderItem orderItem){
        orderItemRepository.save(orderItem);
    }

    public OrderItem updateOrderItems(Long id, OrderItem updatedOrderItem){
        return orderItemRepository.findById(id).map(orderItem -> {

            orderItem.setOrder(updatedOrderItem.getOrder());
            orderItem.setItem(updatedOrderItem.getItem());
            orderItem.setPackageVolume(updatedOrderItem.getPackageVolume());
            orderItem.setTitulli(updatedOrderItem.getTitulli());
            orderItem.setUnitPrice(updatedOrderItem.getUnitPrice());
            orderItem.setRequestedQuantity(updatedOrderItem.getRequestedQuantity());

            return orderItemRepository.save(orderItem);

        }).orElseThrow();
    }

    public List<OrderItem> lexoItems(){
        return orderItemRepository.findAll();
    }

    public void fshiItems(Long id){
        orderItemRepository.deleteById(id);
    }


}
