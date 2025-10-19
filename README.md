# **Inventory Management System (Java Spring Boot, Hibernate, MySQL)**

## **Overview**
This project represents an **Inventory Management System** built using **Java Spring Boot**, following clean architecture principles with a modular and layered structure.  
It allows for complete management of items, orders, deliveries, trucks, and users — supporting secure authentication and role-based access control.

---

## **Features**
- **Item Management** – Create, update, delete, and retrieve items in inventory  
- **Order Management** – Handle customer orders, order items, and statuses  
- **Delivery Tracking** – Manage deliveries and associated trucks  
- **User Authentication** – Secure login/register using JWT tokens  
- **Role-Based Access Control** – Supports roles like *Admin*, *Manager*, and *User*  
- **RESTful APIs** with full CRUD functionality  
- **Swagger Integration** for API documentation  
- **MySQL Data Persistence** with **Spring Data JPA (Hibernate)**  

---

## **Technologies Used**
- **Java 17** – Core programming language  
- **Spring Boot** – Application framework  
- **Spring Security + JWT** – Authentication & authorization  
- **Hibernate / JPA** – ORM for database interaction  
- **MySQL** – Relational database  
- **Maven** – Dependency management  
- **Swagger / OpenAPI** – API documentation  
- **REST Architecture** – Modular and scalable backend structure  

---

## **Main Components**

### **Configuration**
- `PasswordConfig.java` – Configures password encoding  
- `SecurityConfig.java` – Manages authentication and role access  
- `SwaggerConfig.java` – Enables Swagger documentation  
- `WebConfig.java` – Configures web application CORS and global settings  

### **Controllers**
- `AuthController.java` – Handles authentication requests  
- `UserController.java` – Manages user accounts  
- `ItemController.java`, `InventariController.java` – Item and inventory endpoints  
- `OrderController.java`, `OrderItemController.java` – Manage orders and order items  
- `DeliveryController.java`, `TruckController.java` – Delivery and truck management  

### **Models**
- `User.java`, `Role.java` – Represent system users and roles  
- `Item.java`, `Inventari.java` – Inventory and item entities  
- `Order.java`, `OrderItem.java`, `OrderStatus.java` – Order domain models  
- `Delivery.java`, `Truck.java` – Delivery and transportation entities  

### **Repositories**
- `UserRepository.java`, `ItemRepository.java`, `OrderRepository.java`, etc. –  
  Provide structured CRUD and query operations  

### **Security**
- `JwtAuthenticationFilter.java`, `JwtService.java`, `JwtTokenProvider.java` –  
  Implement JWT-based authentication  
- `SecurityConstants.java` – Defines security-related constants  

### **Services**
- Implements business logic for all modules:  
  `UserService.java`, `ItemService.java`, `OrderService.java`,  
  `DeliveryService.java`, `TruckService.java`, etc.  

---

## **Key Highlights**
- **Layered Architecture** – Clear separation of Controller, Service, Repository, and Model layers  
- **Secure Authentication** – JWT tokens with refresh and validation  
- **Scalable Design** – Easily extendable for new entities and endpoints  
- **Data Integrity** – Validation and exception handling at the service level  
- **API-First Approach** – Fully documented REST endpoints via Swagger  


