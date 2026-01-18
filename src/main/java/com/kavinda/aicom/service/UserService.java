package com.kavinda.aicom.service;

import com.kavinda.aicom.model.*;
import com.kavinda.aicom.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService{

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //creaate a user

    public User createUser(User user){
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("email  already exist");
        }
        return userRepository.save(user);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Integer id, User updatedUser) {
        User existing = getUserById(id);
        existing.setName(updatedUser.getName());
        existing.setEmail(updatedUser.getEmail());
        existing.setRole(updatedUser.getRole());
        return userRepository.save(existing);
    }

    public void deleteUser(Integer id) {
        userRepository.delete(getUserById(id));
    }





}
