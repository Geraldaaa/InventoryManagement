package com.inventar.InventoryManagement.controller;

import com.inventar.InventoryManagement.dto.ItemDTO;
import com.inventar.InventoryManagement.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemDTO> addItem(@RequestBody ItemDTO item){
        return ResponseEntity.ok(itemService.createItem(item));
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> readAllItems(){
        return ResponseEntity.ok(itemService.getAllItems());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable Long id){
        ItemDTO item = itemService.getItemById(id);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable Long id, @RequestBody ItemDTO updatedItem){
        ItemDTO item = itemService.updateItem(id, updatedItem);
        return ResponseEntity.ok(item);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id){
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }


    public ItemService getItemService() {
        return itemService;
    }
}
