package com.anurag.fitmono.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class UserResponse {
    @NotBlank(message = "email is required")
    @Email(message = "invalid format")
    private String email;
    private String password;
    @Size(min=6,message = "password mus be more complex")
    @NotBlank(message = "password is required")

    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
