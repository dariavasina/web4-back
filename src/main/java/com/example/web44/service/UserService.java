package com.example.web44.service;

import com.example.web44.model.User;
import com.example.web44.repository.UserRepository;
import com.example.web44.validators.AuthError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public AuthError createUser(User user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()) return AuthError.USER_ALREADY_EXISTS;

        User encodedUser = new User();
        encodedUser.setUsername(user.getUsername());
        encodedUser.setPassword(encodePassword(user.getPassword()));

        //System.out.println("Saving new user with username: " + user.getUsername());
        userRepository.save(encodedUser);
        return null;
    }

    public String encodePassword(String password) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-224");

            // Apply SHA-224 hashing to the password
            byte[] hashedBytes = messageDigest.digest(password.getBytes());

            // Convert the hashed bytes to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
