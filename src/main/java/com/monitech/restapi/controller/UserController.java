package com.monitech.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.monitech.restapi.exception.ValidationException;
import com.monitech.restapi.model.User;
import com.monitech.restapi.service.UserService;

import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/api/favore/v1")
public class UserController {
    
    private final UserService userService;

    private void validateUser(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new ValidationException("Name is required");
        }
        if (user.getName().length()>30) {
            throw new ValidationException("Name must not exceed 30 characters");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new ValidationException("Email is required");
        }
        if (user.getEmail().length()>30) {
            throw new ValidationException("Email must not exceed 30 characters");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (user.getPassword().length()>30) {
            throw new ValidationException("Password must not exceed 30 characters");
        }
    }

    private void validateUserExistence(User user) {
        if (userService.existsByEmail(user.getEmail())) {
            throw new ValidationException("User with email: " + user.getEmail() + " already exists");
        }
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //URL: http://localhost:8080/api/favore/v1/users
    //Method: POST
    @Transactional
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        validateUser(user);
        validateUserExistence(user);
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    //URL: http://localhost:8080/api/favore/v1/users
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //URL: http://localhost:8080/api/favore/v1/users/1
    //Method: GET
    @Transactional
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    //URL: http://localhost:8080/api/favore/v1/users/1
    //Method: PUT
    @Transactional
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id,@RequestBody User user) {
        validateUser(user);
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    //URL: http://localhost:8080/api/favore/v1/users/1
    //Method: DELETE
    @Transactional
    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long id) {
        User deletedUser = userService.deleteUser(id);
        return ResponseEntity.ok(deletedUser);
    }

}
