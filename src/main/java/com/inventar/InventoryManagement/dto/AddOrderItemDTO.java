package com.inventar.InventoryManagement.dto;

public class AddOrderItemDTO {
    private Long itemId;
    private Integer quantity;

    public AddOrderItemDTO() {}

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
