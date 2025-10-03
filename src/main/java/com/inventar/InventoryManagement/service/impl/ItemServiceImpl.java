package com.inventar.InventoryManagement.service.impl;

import com.inventar.InventoryManagement.dto.ItemDTO;
import com.inventar.InventoryManagement.model.Item;
import com.inventar.InventoryManagement.repository.ItemRepositori;
import com.inventar.InventoryManagement.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepositori itemRepositori;

    public ItemServiceImpl(ItemRepositori itemRepositori) {
        this.itemRepositori = itemRepositori;
    }

    @Override
    @Transactional
    public ItemDTO shtoItem(ItemDTO dto) {
        Item item = new Item();
        item.setItemName(dto.getItemName());
        item.setQuantity(dto.getQuantity());
        item.setUnitPrice(dto.getUnitPrice());
        item.setPackageVolume(dto.getPackageVolume());

        item = itemRepositori.save(item);

        dto.setId(item.getId());
        return dto;
    }

    @Override
    @Transactional
    public ItemDTO getItemById(Long id) {
        return itemRepositori.findById(id).map(item -> {
            ItemDTO dto = new ItemDTO();
            dto.setId(item.getId());
            dto.setItemName(item.getItemName());
            dto.setQuantity(item.getQuantity());
            dto.setUnitPrice(item.getUnitPrice());
            dto.setPackageVolume(item.getPackageVolume());
            return dto;
        }).orElse(null);
    }

    @Override
    @Transactional
    public List<ItemDTO> getAllItems() {
        return itemRepositori.findAll()
                .stream()
                .map(item -> {
                    ItemDTO dto = new ItemDTO();
                    dto.setId(item.getId());
                    dto.setItemName(item.getItemName());
                    dto.setQuantity(item.getQuantity());
                    dto.setUnitPrice(item.getUnitPrice());
                    dto.setPackageVolume(item.getPackageVolume());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ItemDTO updateItem(Long id, ItemDTO dto) {
        Item item = itemRepositori.findById(id).orElseThrow();
        item.setItemName(dto.getItemName());
        item.setQuantity(dto.getQuantity());
        item.setUnitPrice(dto.getUnitPrice());
        item.setPackageVolume(dto.getPackageVolume());

        itemRepositori.save(item);
        dto.setId(item.getId());
        return dto;
    }

    @Override
    @Transactional
    public void deleteItem(Long id) {
        itemRepositori.deleteById(id);
    }
}
