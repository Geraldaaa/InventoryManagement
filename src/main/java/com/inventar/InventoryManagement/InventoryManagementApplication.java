package com.inventar.InventoryManagement;

import com.inventar.InventoryManagement.model.*;
import com.inventar.InventoryManagement.service.impl.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.time.LocalDate;

@SpringBootApplication
public class InventoryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(UserService userService,
//						  OrderService orderService,
//						  OrderItemService orderItemService,
//						  ItemService itemService,
//						  InventariService inventariService,
//						  TruckService truckService,
//						  //DeliveryService deliveryService) {
//		return args -> {
//			// ================== CREATE USER ==================
//			User user = new User();
//			user.setFirstName("John");
//			user.setLastName("Doe");
//			user.setUsername("jdoe");
//			user.setAge(30);
//			user.setEmail("john@example.com");
//			user.setPassword("1234");
//			user.setPhoneNumber("0681234567");
//			userService.shtoUser(user);
//
//			// ================== CREATE INVENTORY ==================
//			Inventari inv = new Inventari();
//			inv.setName("Main Warehouse");
//			inv.setLocation("Durres");
//			inventariService.shtoInventar(inv);
//
//			// ================== CREATE ITEM ==================
//			Item item = new Item();
//			item.setItemName("Laptop");
//			item.setQuantity(50);
//			item.setUnitPrice(1000.0);
//			item.setPackageVolume(0.5);
//			item.setInventory(inv);
//			itemService.shtoItem(item);
//
//			// ================== CREATE ORDER ==================
//			Order order = new Order();
//			order.setOrderNumber(1001);
//			order.setSubmittedDate(new Date(System.currentTimeMillis()));
//			order.setDeadlineDate(new Date(System.currentTimeMillis() + 86400000)); // +1 ditë
//			order.setStatus(OrderStatus.PENDING);
//			order.setUser(user);
//			orderService.createOrder(order);
//
//			// ================== CREATE ORDER ITEM ==================
//			OrderItem orderItem = new OrderItem();
//			orderItem.setTitulli("Laptop Order");
//			orderItem.setUnitPrice(item.getUnitPrice());
//			orderItem.setRequestedQuantity(2);
//			orderItem.setPackageVolume(item.getPackageVolume());
//			orderItem.setOrder(order);
//			orderItem.setItem(item);
//			orderItemService.shtoOrderItem(orderItem);
//
//			// ================== CREATE TRUCK ==================
//			Truck truck = new Truck();
//			truck.setChassisNumber("CH123456");
//			truck.setLicensePlate("AA123BB");
//			truck.setContainerVolume(50.0);
//			truckService.shtoTruck(truck);
//
//			// ================== CREATE DELIVERY ==================
//			Delivery delivery = new Delivery();
//			delivery.setDeliveryDate(LocalDate.now());
//			delivery.setTruck(truck);
//			//deliveryService.shtoDelivery(delivery);
//
//			// Lidho order me delivery
//			order.setDelivery(delivery);
//			orderService.updateOrder(order.getId(), order);
//
//			// ================== PRINT ==================
//			System.out.println("All Users: " + userService.lexoUsers());
//			System.out.println("All Inventories: " + inventariService.shfaqInventarin());
//			System.out.println("All Items: " + itemService.lexoItems());
//			System.out.println("All Orders: " + orderService.getAllOrders());
//			System.out.println("All OrderItems: " + orderItemService.lexoOrderItems());
//			System.out.println("All Trucks: " + truckService.lexoTrucks());
//			//System.out.println("All Deliveries: " + deliveryService.lexoDeliveries());
//		};
//	}


}
