package com.fitness.userservice.service;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.model.User;
import com.fitness.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponse register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exist");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstname(request.getFirstName());
        user.setLastName(request.getLastName());

        User savedUser= userRepository.save(user);
        return UserResponse.builder().id(savedUser.getId())
                .email(savedUser.getEmail())
                .firstname(savedUser.getFirstname())
                .lastName(savedUser.getLastName())
                .password(savedUser.getPassword())
                .createdAt(savedUser.getCreatedAt())
                .updatedAt(savedUser.getUpdatedAt())
                .build();

    }

    public UserResponse getUserProfile(String userId) {
       User user= userRepository.findById(userId).
               orElseThrow(()->new RuntimeException("User not found"));

        return UserResponse.builder().id(user.getId())
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}