package com.inventar.InventoryManagement.service.impl;

import com.inventar.InventoryManagement.model.User;
import com.inventar.InventoryManagement.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl {


    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Transactional
    public void shtoUser(User user){
        userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, User updatedUser){
        return userRepository.findById(id).map(user -> {

          user.setFirstName(updatedUser.getFirstName());
          user.setLastName(updatedUser.getLastName());
          user.setUsername(updatedUser.getUsername());
          user.setPassword(updatedUser.getPassword());
          user.setAge(updatedUser.getAge());
          user.setEmail(updatedUser.getEmail());
          user.setPhoneNumber(updatedUser.getPhoneNumber());
          user.setRole(updatedUser.getRole());

            return userRepository.save(user);

        }).orElseThrow();
    }

    @Transactional
    public List<User> lexoUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }




}
