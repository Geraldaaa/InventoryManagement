package com.inventar.InventoryManagement.service.impl;

import com.inventar.InventoryManagement.dto.UserDTO;
import com.inventar.InventoryManagement.model.User;
import com.inventar.InventoryManagement.repository.UserRepository;
import com.inventar.InventoryManagement.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // ================== SHTO USER ==================
    @Transactional
    @Override
    public UserDTO shtoUser(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setRole(dto.getRole());

        // Hash password dhe vendos në entity User
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // <-- Kjo është e rëndësishme

        // Ruaj user në DB
        user = userRepository.save(user);

        // Vendos ID në DTO për kthim
        dto.setId(user.getId());
        // Për siguri, nuk kthejmë password plaintext
        dto.setPassword(null);

        return dto;
    }

    // ================== UPDATE USER ==================
    @Transactional
    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        User updated = userRepository.findById(id).map(user -> {
            user.setUsername(dto.getUsername());
            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setEmail(dto.getEmail());
            user.setAge(dto.getAge());
            user.setPhoneNumber(dto.getPhoneNumber());
            user.setRole(dto.getRole());
            return userRepository.save(user);
        }).orElseThrow();

        dto.setId(updated.getId());
        return dto;
    }

    // ================== LEXO USERS ==================
    @Transactional
    @Override
    public List<UserDTO> lexoUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> {
                    UserDTO dto = new UserDTO();
                    dto.setId(user.getId());
                    dto.setUsername(user.getUsername());
                    dto.setFirstName(user.getFirstName());
                    dto.setLastName(user.getLastName());
                    dto.setEmail(user.getEmail());
                    dto.setAge(user.getAge());
                    dto.setPhoneNumber(user.getPhoneNumber());
                    dto.setRole(user.getRole());
                    return dto;
                })
                .toList();
    }

    // ================== GET USER BY ID ==================
    @Transactional
    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    UserDTO dto = new UserDTO();
                    dto.setId(user.getId());
                    dto.setUsername(user.getUsername());
                    dto.setFirstName(user.getFirstName());
                    dto.setLastName(user.getLastName());
                    dto.setEmail(user.getEmail());
                    dto.setAge(user.getAge());
                    dto.setPhoneNumber(user.getPhoneNumber());
                    dto.setRole(user.getRole());
                    return dto;
                }).orElse(null);
    }

    // ================== DELETE USER ==================
    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
