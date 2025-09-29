package com.inventar.InventoryManagement.controller;

import com.inventar.InventoryManagement.dto.OrderItemDTO;
import com.inventar.InventoryManagement.model.OrderItem;
import com.inventar.InventoryManagement.service.OrderItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("/create")
    public void createOrderItem(@RequestBody OrderItem orderItem) {
        orderItemService.shtoOrderItem(orderItem);
    }

    @PutMapping("/update/{id}")
    public void updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItem) {
        orderItemService.updateOrderItem(id, orderItem);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
    }

    @GetMapping("/all")
    public List<OrderItemDTO> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public OrderItemDTO getOrderItemById(@PathVariable Long id) {
        return orderItemService.getOrderItemById(id);
    }
}
