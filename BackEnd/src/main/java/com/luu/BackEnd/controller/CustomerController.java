package com.luu.BackEnd.controller;

import com.luu.BackEnd.exception.UserAlreadyExistsException;
import com.luu.BackEnd.payload.request.RegisterRequest;
import com.luu.BackEnd.payload.response.ResponseData;
import com.luu.BackEnd.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private UserServiceImp userServiceImp; // Dịch vụ để xử lý logic người dùng

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody RegisterRequest request) {
        try {
                // Call service to register user
            boolean isRegistered = userServiceImp.registerCustomer(request);
            if (isRegistered) {
                // Response when registering successful
                return ResponseEntity.ok(new ResponseData(true, "User registered successfully", request));
            } else {
                // Response when registration fails due to system error or certain conditions
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseData(false, "Registration failed", null));
            }
        } catch (UserAlreadyExistsException e) {
            // Response when encountering errors, for example email already exists
            return ResponseEntity.badRequest().body(new ResponseData(false, "Email is already in use", "EMAIL_ALREADY_EXISTS"));
        }
    }
}

