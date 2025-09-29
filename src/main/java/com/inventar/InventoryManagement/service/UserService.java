package com.inventar.InventoryManagement.service;

import com.inventar.InventoryManagement.dto.UserDTO;
import com.inventar.InventoryManagement.model.User;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO dto);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(Long id, UserDTO dto);

    void deleteUser(Long id);
}

