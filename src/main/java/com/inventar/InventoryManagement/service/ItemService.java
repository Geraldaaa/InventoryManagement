package com.inventar.InventoryManagement.service;

import com.inventar.InventoryManagement.model.Item;
import com.inventar.InventoryManagement.repository.ItemRepositori;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {


    private ItemRepositori itemRepositori;


    public ItemService(ItemRepositori itemRepositori){
        this.itemRepositori = itemRepositori;
    }

    @Transactional
    public void shtoItem(Item item){
        itemRepositori.save(item);
    }

    @Transactional
    public Item updateInventari(Long id, Item updatedItem){
        return itemRepositori.findById(id).map(item -> {

            item.setItemName(updatedItem.getItemName());
            item.setQuantity(updatedItem.getQuantity());
            item.setPackageVolume(updatedItem.getPackageVolume());
            item.setUnitPrice(updatedItem.getUnitPrice());

            return itemRepositori.save(item);

        }).orElseThrow();
    }

    @Transactional
    public List<Item> lexoItems(){
       return itemRepositori.findAll();
    }

    @Transactional
    public void fshiItems(Long id){
        itemRepositori.deleteById(id);
    }

}
