package com.example.web44.controller;


import com.example.web44.model.User;
import com.example.web44.repository.UserRepository;
import com.example.web44.service.UserService;
import com.example.web44.utils.JwtTokenUtil;
import com.example.web44.validators.AuthError;
import com.example.web44.validators.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin(origins = {"https://localhost:3000"})
@RestController
@RequestMapping("/api/v1/")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        AuthError authError = new UserValidation().validateUser(user.getUsername(), user.getPassword());
        if(authError != null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(authError.getErrorMessage());
        }

        AuthError userValidation = userService.createUser(user);
        if (userValidation != null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userValidation.getErrorMessage());
        }

        return ResponseEntity.ok().body("ok");
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        AuthError authError = new UserValidation().validateUser(user.getUsername(), user.getPassword());
        if(authError != null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authError.getErrorMessage());
        }

        Optional<User> optionalExistingUser = userRepository.findByUsername(user.getUsername());
        if (optionalExistingUser.isPresent()) {
            User existingUser = optionalExistingUser.get();
            String password = userService.encodePassword(user.getPassword());
            if (!existingUser.getPassword().equals(password)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        }

        String token = JwtTokenUtil.generateToken(user.getUsername());
        return ResponseEntity.ok().body(token);
    }


}
