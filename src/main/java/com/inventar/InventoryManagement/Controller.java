package com.inventar.InventoryManagement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/api/test")
    public String sayHello() {
        return "Hello Spring!";
    }
}
