package com.inventar.InventoryManagement.dto;

import com.inventar.InventoryManagement.model.OrderStatus;
import java.util.Date;
import java.util.List;

public class OrderDTO {
    private Long id;
    private Integer orderNumber;
    private Date submittedDate;
    private OrderStatus status;
    private Date deadlineDate;
    private Long userId;
    private Long deliveryId;
    private List<OrderItemDTO> orderItems;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getOrderNumber() { return orderNumber; }
    public void setOrderNumber(Integer orderNumber) { this.orderNumber = orderNumber; }
    public Date getSubmittedDate() { return submittedDate; }
    public void setSubmittedDate(Date submittedDate) { this.submittedDate = submittedDate; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public Date getDeadlineDate() { return deadlineDate; }
    public void setDeadlineDate(Date deadlineDate) { this.deadlineDate = deadlineDate; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getDeliveryId() { return deliveryId; }
    public void setDeliveryId(Long deliveryId) { this.deliveryId = deliveryId; }
    public List<OrderItemDTO> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItemDTO> orderItems) { this.orderItems = orderItems; }
}
