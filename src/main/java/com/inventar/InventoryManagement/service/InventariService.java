package com.inventar.InventoryManagement.service;

import com.inventar.InventoryManagement.model.Inventari;

import java.util.List;

public interface InventariService {
    void shtoInventar(Inventari inventari);
    Inventari updateInventar(Long id, Inventari updatedInventari);
    List<Inventari> shfaqInventarin();
    void fshiInventar(Long id);
}
