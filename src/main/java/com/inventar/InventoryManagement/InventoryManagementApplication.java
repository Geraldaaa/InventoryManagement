package com.inventar.InventoryManagement;

import com.inventar.InventoryManagement.model.Order;
import com.inventar.InventoryManagement.service.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(OrderService orderService) {
		return args -> {
			Order o1 = new Order();



			Order savedOrder = orderService.createOrder(o1);
			System.out.println("Order saved with ID: " + savedOrder.getId());
		};
	}

}
