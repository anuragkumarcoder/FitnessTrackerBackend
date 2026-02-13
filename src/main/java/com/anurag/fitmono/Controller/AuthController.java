package com.anurag.fitmono.Controller;

import com.anurag.fitmono.Services.UserServices;
import com.anurag.fitmono.dto.LoginRequest;
import com.anurag.fitmono.dto.RegisterRequest;
import com.anurag.fitmono.dto.UserResponse;
import com.anurag.fitmono.dto.loginResponse;
import com.anurag.fitmono.model.User;
import com.anurag.fitmono.security.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserServices userServices;
    private final JwtUtils jwtUtils;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId) {
        return ResponseEntity.ok(userServices.getUserProfile(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userServices.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<loginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        User user = userServices.authenticate(loginRequest);
        String token = jwtUtils.generateToken(user.getId(), user.getRole().name());
        return ResponseEntity.ok(new loginResponse(token, userServices.mapToResponse(user)));
    }
}