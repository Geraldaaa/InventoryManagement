package com.inventar.InventoryManagement.service.impl;


import com.inventar.InventoryManagement.dto.OrderItemDTO;
import com.inventar.InventoryManagement.model.OrderItem;
import com.inventar.InventoryManagement.repository.ItemRepositori;
import com.inventar.InventoryManagement.repository.OrderItemRepository;
import com.inventar.InventoryManagement.repository.OrderRepository;
import com.inventar.InventoryManagement.service.OrderItemService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ItemRepositori itemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderRepository orderRepository, ItemRepositori itemRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

   @Override
    @Transactional
    public void shtoOrderItem(OrderItem orderItem) {
         orderItemRepository.save(orderItem);
    }


    @Override
    @Transactional
    public void updateOrderItem(Long id, OrderItem orderItem) {
        OrderItem existing = orderItemRepository.findById(id).orElseThrow();

        existing.setTitulli(orderItem.getTitulli());
        existing.setUnitPrice(orderItem.getUnitPrice());
        existing.setPackageVolume(orderItem.getPackageVolume());
        existing.setRequestedQuantity(orderItem.getRequestedQuantity());
        existing.setItem(orderItem.getItem());
        existing.setOrder(orderItem.getOrder());

        orderItemRepository.save(existing);
    }

    @Override
    @Transactional
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<OrderItemDTO> getAllOrderItems() {
        return orderItemRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    @Transactional
    public OrderItemDTO getOrderItemById(Long id) {
        return orderItemRepository.findById(id).map(this::mapToDTO).orElseThrow();

    }

    @Transactional
    private OrderItemDTO mapToDTO(OrderItem orderItem) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(orderItem.getId());
        dto.setTitulli(orderItem.getTitulli());
        dto.setUnitPrice(orderItem.getUnitPrice());
        dto.setPackageVolume(orderItem.getPackageVolume());
        dto.setRequestedQuantity(orderItem.getRequestedQuantity());
        dto.setItemId(orderItem.getItem() != null ? orderItem.getItem().getId() : null);
        dto.setOrderId(orderItem.getOrder() != null ? orderItem.getOrder().getId() : null);
        return dto;
    }


    public OrderItemRepository getOrderItemRepository() {
        return orderItemRepository;
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    public ItemRepositori getItemRepository() {
        return itemRepository;
    }
}
