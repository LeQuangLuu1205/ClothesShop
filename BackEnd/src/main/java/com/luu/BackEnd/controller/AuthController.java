package com.luu.BackEnd.controller;

import com.luu.BackEnd.exception.UserAlreadyExistsException;
import com.luu.BackEnd.payload.request.RegisterRequest;
import com.luu.BackEnd.payload.response.ResponseData;
import com.luu.BackEnd.service.Imp.UserServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserServiceImp userServiceImp;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest, BindingResult bindingResult) {
        // Kiểm tra các lỗi validation
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error -> {
                errorMessage.append(error.getDefaultMessage()).append("; ");
            });
            return ResponseEntity.badRequest().body(new ResponseData(false, errorMessage.toString(), null));
        }

        try {
            // Gọi service để đăng ký người dùng
            userServiceImp.registerCustomer(registerRequest);
            return ResponseEntity.ok(new ResponseData(true, "User registered successfully", registerRequest));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(new ResponseData(false, e.getMessage(), null));
        } catch (Exception e) {
            // Bắt mọi ngoại lệ khác
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(false, "An error occurred: " + e.getMessage(), null));
        }
    }
}
