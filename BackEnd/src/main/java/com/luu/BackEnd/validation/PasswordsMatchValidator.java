package com.luu.BackEnd.validation;

import com.luu.BackEnd.payload.request.RegisterRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, RegisterRequest> {
    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
    }
    @Override
    public boolean isValid(RegisterRequest request, ConstraintValidatorContext context) {
        // Kiểm tra xem password và confirmPassword có khớp nhau không
        return request.getPassword().equals(request.getConfirmPassword());
    }
}