package com.luu.BackEnd.payload.request;

import com.luu.BackEnd.validation.PasswordsMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PasswordsMatch
public class RegisterRequest {
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Confirm password cannot be empty")
    private String confirmPassword;

    @NotBlank(message = "Fullname cannot be empty")
    private String fullname;

    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "Phone number must be numeric and between 10 and 15 digits and can start with '+'")
    private String phoneNumber;
}
