package com.inventar.InventoryManagement.repository;

import com.inventar.InventoryManagement.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepositori extends JpaRepository<Item, Long> {
}
