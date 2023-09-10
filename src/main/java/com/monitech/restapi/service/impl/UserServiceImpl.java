package com.monitech.restapi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitech.restapi.model.User;
import com.monitech.restapi.repository.UserRepository;
import com.monitech.restapi.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(Long id, User user) {
        User userToUpdate = userRepository.findById(id).orElse(null);
        if (userToUpdate != null) {
            userToUpdate.setName(user.getName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setPassword(user.getPassword());
            userToUpdate.setUpdatedAt(user.getUpdatedAt());
            userToUpdate.setImageUrl(user.getImageUrl());
            userToUpdate.setActive(user.getActive());
            userToUpdate.setType(user.getType());
            return userRepository.save(userToUpdate);
        }
        return null;
    }

    @Override
    public User deleteUser(Long id) {
        User userToDelete = userRepository.findById(id).orElse(null);
        if (userToDelete != null) {
            userRepository.delete(userToDelete);
            return userToDelete;
        }
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
