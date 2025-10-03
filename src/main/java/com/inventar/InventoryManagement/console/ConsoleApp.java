package com.inventar.InventoryManagement.console;

import com.inventar.InventoryManagement.dto.OrderDTO;
import com.inventar.InventoryManagement.dto.UserDTO;
import com.inventar.InventoryManagement.model.OrderStatus;
import com.inventar.InventoryManagement.model.Role;
import com.inventar.InventoryManagement.model.User;
import com.inventar.InventoryManagement.service.OrderService;
import com.inventar.InventoryManagement.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ConsoleApp implements CommandLineRunner {

    private final UserService userService;
    private final OrderService orderService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ConsoleApp(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Welcome to Inventory Console App ===");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 1) {
                // ================= REGISTER USER =================
                UserDTO dto = new UserDTO();
                System.out.print("Enter username: ");
                dto.setUsername(scanner.nextLine());

                System.out.print("Enter password: ");
                dto.setPassword(scanner.nextLine());

                System.out.print("Enter first name: ");
                dto.setFirstName(scanner.nextLine());

                System.out.print("Enter last name: ");
                dto.setLastName(scanner.nextLine());

                System.out.print("Enter email: ");
                dto.setEmail(scanner.nextLine());

                System.out.print("Enter age: ");
                dto.setAge(Integer.parseInt(scanner.nextLine()));

                System.out.print("Enter phone number: ");
                dto.setPhoneNumber(scanner.nextLine());

                // Zgjidh role
                System.out.println("Choose role: 1.User  2.Manager");
                int roleChoice = Integer.parseInt(scanner.nextLine());
                dto.setRole(roleChoice == 1 ? Role.ROLE_USER : Role.ROLE_MANAGER);

                dto = userService.shtoUser(dto);
                System.out.println("✅ User created with ID: " + dto.getId());

            } else if (choice == 2) {
                // ================= LOGIN =================
                System.out.print("Enter username: ");
                String username = scanner.nextLine();

                System.out.print("Enter password: ");
                String inputPassword = scanner.nextLine();

                System.out.println("Choose role: 1.User  2.Manager");
                int roleChoice = Integer.parseInt(scanner.nextLine());
                Role selectedRole = roleChoice == 1 ? Role.ROLE_USER : Role.ROLE_MANAGER;

                Optional<User> userOpt = userService.findByUsername(username);

                if (userOpt.isPresent()) {
                    User user = userOpt.get();

                    if (passwordEncoder.matches(inputPassword, user.getPassword())
                            && user.getRole() == selectedRole) {
                        System.out.println(" Login successful! Welcome, " + user.getFirstName());

                        // Optionally: CREATE ORDER
                        System.out.println("Do you want to create an order? (y/n): ");
                        String answer = scanner.nextLine();
                        if (answer.equalsIgnoreCase("y")) {
                            OrderDTO orderDto = new OrderDTO();
                            orderDto.setSubmittedDate(new Date());
                            orderDto.setStatus(OrderStatus.CREATED);

                            OrderDTO createdOrder = orderService.krijoOrder(user.getId(), orderDto);
                            System.out.println(" Order created with number: " + createdOrder.getOrderNumber());
                        }

                    } else {
                        System.out.println(" Invalid username, password or role.");
                    }
                } else {
                    System.out.println(" User not found.");
                }

            } else if (choice == 3) {
                System.out.println(" Exiting app...");
                break;
            } else {
                System.out.println(" Invalid option. Try again.");
            }
        }
    }
}
