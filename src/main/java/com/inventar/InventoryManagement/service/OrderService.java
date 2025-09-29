package com.inventar.InventoryManagement.service;

import com.inventar.InventoryManagement.dto.*;
import com.inventar.InventoryManagement.model.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderDTO krijoOrder(Long userId, OrderDTO dto);
    OrderDTO addItemToOrder(Long userId, Long orderId, AddOrderItemDTO dto);
    OrderDTO updateOrderItemQuantity(Long userId, Long orderId, Long orderItemId, int quantity);
    OrderDTO removeItemFromOrder(Long userId, Long orderId, Long orderItemId);
    OrderDTO submitOrder(Long userId, Long orderId);
    OrderDTO cancelOrder(Long userId, Long orderId);
    List<OrderDTO> getOrdersForUser(Long userId, Optional<OrderStatus> status);




//manager
    OrderDTO approveOrder(Long managerId, Long orderId);
    OrderDTO declineOrder(Long managerId, Long orderId);
    List<OrderDTO> getAllOrders(Optional<OrderStatus> status);
}
