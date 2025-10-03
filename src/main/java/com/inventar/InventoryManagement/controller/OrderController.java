package com.inventar.InventoryManagement.controller;

import com.inventar.InventoryManagement.dto.AddOrderItemDTO;
import com.inventar.InventoryManagement.dto.OrderDTO;
import com.inventar.InventoryManagement.model.OrderStatus;
import com.inventar.InventoryManagement.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // ---------------- Client Actions ----------------*---------------------*-----------------------------*------------------------------*-----------------

    @PostMapping("/create/{userId}")
    public OrderDTO createOrder(@PathVariable Long userId, @RequestBody OrderDTO dto) {
        return orderService.krijoOrder(userId, dto);
    }

    @PostMapping("/{userId}/{orderId}/addItem")
    public OrderDTO addItemToOrder(@PathVariable Long userId,
                                   @PathVariable Long orderId,
                                   @RequestBody AddOrderItemDTO dto) {
        return orderService.addItemToOrder(userId, orderId, dto);
    }

    @PutMapping("/{userId}/{orderId}/updateItem/{orderItemId}")
    public OrderDTO updateOrderItemQuantity(@PathVariable Long userId,
                                            @PathVariable Long orderId,
                                            @PathVariable Long orderItemId,
                                            @RequestParam int quantity) {
        return orderService.updateOrderItemQuantity(userId, orderId, orderItemId, quantity);
    }

    @DeleteMapping("/{userId}/{orderId}/removeItem/{orderItemId}")
    public OrderDTO removeItemFromOrder(@PathVariable Long userId,
                                        @PathVariable Long orderId,
                                        @PathVariable Long orderItemId) {
        return orderService.removeItemFromOrder(userId, orderId, orderItemId);
    }

    @PutMapping("/{userId}/{orderId}/submit")
    public OrderDTO submitOrder(@PathVariable Long userId, @PathVariable Long orderId) {
        return orderService.submitOrder(userId, orderId);
    }

    @PutMapping("/{userId}/{orderId}/cancel")
    public OrderDTO cancelOrder(@PathVariable Long userId, @PathVariable Long orderId) {
        return orderService.cancelOrder(userId, orderId);
    }

    @GetMapping("/{userId}/list")
    public List<OrderDTO> getOrdersForUser(@PathVariable Long userId, @RequestParam(required = false) OrderStatus status) {
        return orderService.getOrdersForUser(userId, Optional.ofNullable(status));
    }

    // ---------------- Manager Actions -------------------------------------+------------------------------------+---------+---------------------------------------

    @PutMapping("/approve/{managerId}/{orderId}")
    public OrderDTO approveOrder(@PathVariable Long managerId, @PathVariable Long orderId) {
        return orderService.approveOrder(managerId, orderId);
    }

    @PutMapping("/decline/{managerId}/{orderId}")
    public OrderDTO declineOrder(@PathVariable Long managerId, @PathVariable Long orderId) {
        return orderService.declineOrder(managerId, orderId);
    }

    @GetMapping("/all")
    public List<OrderDTO> getAllOrders(@RequestParam(required = false) OrderStatus status) {
        return orderService.getAllOrders(Optional.ofNullable(status));
    }
}
