package com.luu.BackEnd.payload.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    @NotEmpty(message = "Username must not be empty")
    private String username;
    @NotEmpty(message = "Password must not be empty")
    private String password;
}
