package com.inventar.InventoryManagement.dto;

public class OrderItemDTO {
    private Long id;
    private String titulli;
    private Double unitPrice;
    private Double packageVolume;
    private Integer requestedQuantity;
    private Long itemId;
    private Long orderId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulli() { return titulli; }
    public void setTitulli(String titulli) { this.titulli = titulli; }
    public Double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }
    public Double getPackageVolume() { return packageVolume; }
    public void setPackageVolume(Double packageVolume) { this.packageVolume = packageVolume; }
    public Integer getRequestedQuantity() { return requestedQuantity; }
    public void setRequestedQuantity(Integer requestedQuantity) { this.requestedQuantity = requestedQuantity; }
    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
}
