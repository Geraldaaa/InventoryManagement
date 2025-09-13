package com.inventar.InventoryManagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventari inventory;

    private Integer quantity;
    private Double unitPrice;
    private Double packageVolume;

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getPackageVolume() {
        return packageVolume;
    }

    public void setPackageVolume(Double packageVolume) {
        this.packageVolume = packageVolume;
    }
}
