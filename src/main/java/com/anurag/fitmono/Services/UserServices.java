package com.anurag.fitmono.Services;

import com.anurag.fitmono.Repository.UserRepository;
import com.anurag.fitmono.dto.LoginRequest;
import com.anurag.fitmono.dto.RegisterRequest;
import com.anurag.fitmono.dto.UserResponse;
import com.anurag.fitmono.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServices {
    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(RegisterRequest request) {
        if (repo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("user already exist");
        }
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = repo.save(user);
        return mapToResponse(savedUser);
    }

    public User authenticate(LoginRequest request) {
        User user = repo.findByEmail(request.getEmail());
        if (user == null) {
            throw new RuntimeException("Invalid credentials");
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return user;
    }

    public UserResponse getUserProfile(String userId) {
        User user = repo.findById(userId)
                .orElseThrow(() -> new RuntimeException("user does not exist"));
        return mapToResponse(user);
    }

    public UserResponse mapToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());
        return userResponse;
    }
}