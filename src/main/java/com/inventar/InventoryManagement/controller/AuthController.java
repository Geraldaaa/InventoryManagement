package com.inventar.InventoryManagement.controller;

import com.inventar.InventoryManagement.dto.AuthResponseDTO;
import com.inventar.InventoryManagement.dto.LoginRequestDTO;
import com.inventar.InventoryManagement.dto.RegisterRequestDTO;
import com.inventar.InventoryManagement.model.Role;
import com.inventar.InventoryManagement.model.User;
import com.inventar.InventoryManagement.repository.UserRepository;
import com.inventar.InventoryManagement.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public AuthResponseDTO register(@RequestBody RegisterRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setRole(Role.ROLE_USER);

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthResponseDTO(token, user.getUsername(), user.getRole().name());
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody LoginRequestDTO dto) {
        User user = userRepository.findAll()
                .stream()
                .filter(u -> u.getUsername().equals(dto.getUsername()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponseDTO(token, user.getUsername(), user.getRole().name());
    }
}
