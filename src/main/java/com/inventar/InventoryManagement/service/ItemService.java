package com.inventar.InventoryManagement.service;

import com.inventar.InventoryManagement.dto.ItemDTO;
import com.inventar.InventoryManagement.model.Item;

import java.util.List;

public interface ItemService {

    // Methods using DTO
    ItemDTO shtoItem(ItemDTO dto);
    ItemDTO getItemById(Long id);
    List<ItemDTO> getAllItems();
    ItemDTO updateItem(Long id, ItemDTO dto);
    void deleteItem(Long id);

}
