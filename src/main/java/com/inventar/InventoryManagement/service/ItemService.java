package com.inventar.InventoryManagement.service;

import com.inventar.InventoryManagement.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    ItemDTO createItem(ItemDTO dto);
    ItemDTO getItemById(Long id);
    List<ItemDTO> getAllItems();
    ItemDTO updateItem(Long id, ItemDTO dto);
    void deleteItem(Long id);
}
