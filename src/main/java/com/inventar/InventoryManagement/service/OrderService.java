package com.inventar.InventoryManagement.service;


import com.inventar.InventoryManagement.model.Order;
import com.inventar.InventoryManagement.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {


    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        return orderRepository.findById(id).map(order -> {

            order.setOrderNumber(updatedOrder.getOrderNumber());
            order.setSubmittedDate(updatedOrder.getSubmittedDate());
            order.setStatus(updatedOrder.getStatus());
            order.setDeadlineDate(updatedOrder.getDeadlineDate());
            order.setUser(updatedOrder.getUser());
            order.setOrderItems(updatedOrder.getOrderItems());
            order.setDelivery(updatedOrder.getDelivery());

            return orderRepository.save(order);

        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
