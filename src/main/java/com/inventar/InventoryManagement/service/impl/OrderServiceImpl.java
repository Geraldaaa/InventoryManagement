package com.inventar.InventoryManagement.service.impl;

import com.inventar.InventoryManagement.dto.*;
import com.inventar.InventoryManagement.model.*;
import com.inventar.InventoryManagement.repository.*;
import com.inventar.InventoryManagement.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepositori itemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, OrderItemRepository orderItemRepository, ItemRepositori itemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderItemRepository = orderItemRepository;
        this.itemRepository = itemRepository;
    }

    // ---------------- Client Actions ----------------

    @Override
    @Transactional
    public OrderDTO krijoOrder(Long userId, OrderDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow();

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        order.setSubmittedDate(new java.util.Date());
        order.setOrderNumber(generateOrderNumber());

        orderRepository.save(order);
        return mapToDTO(order);
    }

    @Override
    @Transactional
    public OrderDTO addItemToOrder(Long userId, Long orderId, AddOrderItemDTO dto) {
        Order order = validateOrderOwnership(orderId, userId);
        validateOrderModifiable(order);

        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow();

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setRequestedQuantity(dto.getQuantity());
        orderItem.setOrder(order);

        order.getOrderItems().add(orderItem);
        orderRepository.save(order);

        return mapToDTO(order);
    }

    @Override
    @Transactional
    public OrderDTO updateOrderItemQuantity(Long userId, Long orderId, Long orderItemId, int quantity) {
        Order order = validateOrderOwnership(orderId, userId);
        validateOrderModifiable(order);

        OrderItem orderItem = order.getOrderItems().stream()
                .filter(oi -> oi.getId().equals(orderItemId))
                .findFirst()
                .orElseThrow();

        orderItem.setRequestedQuantity(quantity);
        orderRepository.save(order);

        return mapToDTO(order);
    }

    @Override
    @Transactional
    public OrderDTO removeItemFromOrder(Long userId, Long orderId, Long orderItemId) {
        Order order = validateOrderOwnership(orderId, userId);
        validateOrderModifiable(order);

        order.getOrderItems().removeIf(oi -> oi.getId().equals(orderItemId));
        orderRepository.save(order);

        return mapToDTO(order);
    }

    @Override
    @Transactional
    public OrderDTO submitOrder(Long userId, Long orderId) {
        Order order = validateOrderOwnership(orderId, userId);
        if (!(order.getStatus() == OrderStatus.CREATED || order.getStatus() == OrderStatus.CANCELLED)) {
            System.out.println("Order cannot be submitted in current status");
        }
        order.setStatus(OrderStatus.AWAITING_APPROVAL);
        orderRepository.save(order);
        return mapToDTO(order);
    }

    @Override
    @Transactional
    public OrderDTO cancelOrder(Long userId, Long orderId) {
        Order order = validateOrderOwnership(orderId, userId);
        if (order.getStatus() == OrderStatus.FULFILLED ||
                order.getStatus() == OrderStatus.UNDER_DELIVERY ||
                order.getStatus() == OrderStatus.CANCELLED) {
            System.out.println("Order cannot be canceled in current status");
        }
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        return mapToDTO(order);
    }

    @Override
    public List<OrderDTO> getOrdersForUser(Long userId, Optional<OrderStatus> status) {
        return orderRepository.findById(userId)
                .stream()
                .filter(order -> status.map(s -> order.getStatus() == s).orElse(true))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    // ---------------- Manager Actions ----------------

    @Override
    @Transactional
    public OrderDTO approveOrder(Long managerId, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow();
        if (order.getStatus() != OrderStatus.AWAITING_APPROVAL) {
            System.out.println("Order not awaiting approval");
        }
        order.setStatus(OrderStatus.APPRUVED);
        orderRepository.save(order);
        return mapToDTO(order);
    }

    @Override
    @Transactional
    public OrderDTO declineOrder(Long managerId, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow();
        if (order.getStatus() != OrderStatus.AWAITING_APPROVAL) {
            System.out.println("Order not awaiting approval");
        }
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        return mapToDTO(order);
    }

    @Override
    public List<OrderDTO> getAllOrders(Optional<OrderStatus> status) {
        return orderRepository.findAll()
                .stream()
                .filter(order -> status.map(s -> order.getStatus() == s).orElse(true))
                .map(this::mapToDTO)
                .toList();
    }


    // ---------------- Helpers ----------------

    private Order validateOrderOwnership(Long orderId, Long userId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow();
        if (!order.getUser().getId().equals(userId)) {
            System.out.println("You do not own this order");
        }
        return order;
    }

    private void validateOrderModifiable(Order order) {
        if (!(order.getStatus() == OrderStatus.CREATED || order.getStatus() == OrderStatus.CANCELLED)) {
            System.out.println("Order cannot be modified in current status");
        }
    }

    private int generateOrderNumber() {
        return (int) (Math.random() * 1_000_000);
    }

    private OrderDTO mapToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderNumber(order.getOrderNumber());
        dto.setStatus(order.getStatus());
        dto.setSubmittedDate(order.getSubmittedDate());
        dto.setOrderItems(order.getOrderItems().stream()
                .map(oi -> {
                    OrderItemDTO oItem = new OrderItemDTO();
                    oItem.setId(oi.getId());
                    oItem.setTitulli(oi.getTitulli());
                    oItem.setUnitPrice(oi.getUnitPrice());
                    oItem.setPackageVolume(oi.getPackageVolume());
                    oItem.setRequestedQuantity(oi.getRequestedQuantity());
                    oItem.setItemId(oi.getItem() != null ? oi.getItem().getId() : null);
                    return oItem;
                }).collect(Collectors.toList()));
        return dto;
    }
}
