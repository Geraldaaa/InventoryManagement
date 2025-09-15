package com.inventar.InventoryManagement.service;


import com.inventar.InventoryManagement.model.OrderItem;
import com.inventar.InventoryManagement.repository.OrderItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderItemService {

    private OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository){
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public void shtoOrderItem(OrderItem orderItem){
        orderItemRepository.save(orderItem);
    }

    @Transactional
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

    @Transactional
    public List<OrderItem> lexoOrderItems(){
        return orderItemRepository.findAll();
    }

    @Transactional
    public void fshiItems(Long id){
        orderItemRepository.deleteById(id);
    }


}
