package com.anurag.fitmono.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserResponse {
    @NotBlank(message = "email is required")
    @Email(message = "invalid format")
    private String email;

    private String password;

    @NotBlank(message = "First name is required")
    private String firstName;

    private String lastName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}