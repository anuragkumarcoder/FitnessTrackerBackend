package com.anurag.fitmono.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class loginResponse {
    private String token;
    private UserResponse user;
}
