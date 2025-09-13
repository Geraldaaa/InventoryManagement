package com.inventar.InventoryManagement.repository;

import com.inventar.InventoryManagement.model.Inventari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventariRepository extends JpaRepository<Inventari,Long> {
}
