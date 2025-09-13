package com.inventar.InventoryManagement.service;

import com.inventar.InventoryManagement.model.Inventari;
import com.inventar.InventoryManagement.repository.InventariRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventariService {

   private InventariRepository inventariRepository;

   public InventariService(InventariRepository inventariRepository){
       this.inventariRepository = inventariRepository;
   }

   public void shtoInventar(Inventari inventari){
       inventariRepository.save(inventari);
   }

   public Inventari updateInventar(Long id, Inventari updatedInventari){
       return inventariRepository.findById(id).map(inventari ->{

           inventari.setName(updatedInventari.getName());
           inventari.setItems(updatedInventari.getItems());
           inventari.setLocation(updatedInventari.getLocation());

           return inventariRepository.save(inventari);
       }).orElseThrow();
   }

   public List<Inventari> shfaqInventarin(){
       return inventariRepository.findAll();
   }

   public void fshiInventar(Long id){
       inventariRepository.deleteById(id);
   }

}
