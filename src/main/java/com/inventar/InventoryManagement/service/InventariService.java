package com.inventar.InventoryManagement.service;

import com.inventar.InventoryManagement.model.Inventari;
import com.inventar.InventoryManagement.repository.InventariRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventariService {

   private InventariRepository inventariRepository;

   public InventariService(InventariRepository inventariRepository){
       this.inventariRepository = inventariRepository;
   }

   @Transactional
   public void shtoInventar(Inventari inventari){
       inventariRepository.save(inventari);
   }

   @Transactional
   public Inventari updateInventar(Long id, Inventari updatedInventari){
       return inventariRepository.findById(id).map(inventari ->{

           inventari.setName(updatedInventari.getName());
           inventari.setItems(updatedInventari.getItems());
           inventari.setLocation(updatedInventari.getLocation());

           return inventariRepository.save(inventari);
       }).orElseThrow();
   }

   @Transactional
   public List<Inventari> shfaqInventarin(){
       return inventariRepository.findAll();
   }

   @Transactional
   public void fshiInventar(Long id){
       inventariRepository.deleteById(id);
   }

}
