package com.inventar.InventoryManagement.service.impl;

import com.inventar.InventoryManagement.model.Inventari;
import com.inventar.InventoryManagement.repository.InventariRepository;
import com.inventar.InventoryManagement.service.InventariService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventariServiceImpl implements InventariService {

    private final InventariRepository inventariRepository;

    public InventariServiceImpl(InventariRepository inventariRepository){
        this.inventariRepository = inventariRepository;
    }

    @Override
    @Transactional
    public void shtoInventar(Inventari inventari){
        inventariRepository.save(inventari);
    }

    @Override
    @Transactional
    public Inventari updateInventar(Long id, Inventari updatedInventari){
        return inventariRepository.findById(id).map(inventari -> {
            inventari.setName(updatedInventari.getName());
            inventari.setItems(updatedInventari.getItems());
            inventari.setLocation(updatedInventari.getLocation());
            return inventariRepository.save(inventari);
        }).orElseThrow(() -> new RuntimeException("Inventari not found"));
    }

    @Override
    @Transactional
    public List<Inventari> shfaqInventarin(){
        return inventariRepository.findAll();
    }

    @Override
    @Transactional
    public void fshiInventar(Long id){
        inventariRepository.deleteById(id);
    }
}
