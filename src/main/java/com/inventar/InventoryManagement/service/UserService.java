package com.inventar.InventoryManagement.service;

import com.inventar.InventoryManagement.dto.UserDTO;
import com.inventar.InventoryManagement.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO shtoUser(UserDTO dto);

    UserDTO getUserById(Long id);

    List<UserDTO> lexoUsers();

    UserDTO updateUser(Long id, UserDTO dto);

    void deleteUser(Long id);

    void save(User user);
    Optional<User> findByUsername(String username);
}

