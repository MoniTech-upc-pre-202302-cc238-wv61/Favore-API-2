package com.monitech.restapi.service;

import java.util.List;

import com.monitech.restapi.model.User;

public interface UserService {
    
    User createUser(User user);

    User updateUser(Long id, User user);

    User getUserById(Long id);

    boolean existsByEmail(String email);

    User deleteUser(Long id);

    List<User> getAllUsers();

    User findByEmail(String email);
}
