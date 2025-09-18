package com.inventar.InventoryManagement.dto;

public class ItemDTO {
    private Long id;
    private String itemName;
    private Integer quantity;
    private Double unitPrice;
    private Double packageVolume;
    private Long inventoryId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }
    public Double getPackageVolume() { return packageVolume; }
    public void setPackageVolume(Double packageVolume) { this.packageVolume = packageVolume; }
    public Long getInventoryId() { return inventoryId; }
    public void setInventoryId(Long inventoryId) { this.inventoryId = inventoryId; }
}
