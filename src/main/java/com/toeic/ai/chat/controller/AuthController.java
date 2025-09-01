
package com.toeic.ai.chat.controller;

import com.toeic.ai.chat.model.User;
import com.toeic.ai.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.isUsernameTaken(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        if (userService.isEmailTaken(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already taken");
        }
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        if (userService.authenticate(user.getUsername(), user.getPasswordHash())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
