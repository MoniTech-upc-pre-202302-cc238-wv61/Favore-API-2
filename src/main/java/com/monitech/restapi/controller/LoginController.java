package com.monitech.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.monitech.restapi.dto.UserCredentialsDTO;
import com.monitech.restapi.exception.ValidationException;
import com.monitech.restapi.model.User;
import com.monitech.restapi.service.UserService;

import org.springframework.transaction.annotation.Transactional;
@RestController
@RequestMapping("/api/favore/v1")
public class LoginController {

    private final UserService userService;

    private void validateUserExistence(UserCredentialsDTO user) {
        if (userService.existsByEmail(user.getEmail())) {
            throw new ValidationException("User with email: " + user.getEmail() + " already exists");
        }
    }

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    //URL: http://localhost:8080/api/favore/v1/login
    //Method: POST
    @Transactional
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserCredentialsDTO user_cred) {
        validateUserExistence(user_cred);

        User user = userService.findByEmail(user_cred.getEmail());
        if (user.getPassword().equals(user_cred.getPassword())) {
            return ResponseEntity.ok(user);
        }
        else {
            throw new ValidationException("Wrong password");
        }
    }
    
}
