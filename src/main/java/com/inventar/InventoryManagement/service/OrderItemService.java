package com.inventar.InventoryManagement.service;

import com.inventar.InventoryManagement.dto.OrderItemDTO;
import com.inventar.InventoryManagement.model.OrderItem;
import java.util.List;

public interface OrderItemService {

    public List<OrderItemDTO> getAllOrderItems();

    public OrderItemDTO getOrderItemById(Long id);

    public void shtoOrderItem(OrderItem orderItem);

    public void updateOrderItem(Long id, OrderItem orderItem);

    public void deleteOrderItem(Long id);
}
