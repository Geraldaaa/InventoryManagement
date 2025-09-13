package com.inventar.InventoryManagement.service;

import com.inventar.InventoryManagement.model.User;
import com.inventar.InventoryManagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void shtoUser(User user){
        userRepository.save(user);
    }

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

    public List<User> lexoUsers(){
        return userRepository.findAll();
    }

    public void fshiUser(Long id){
        userRepository.deleteById(id);
    }




}
