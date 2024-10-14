package com.luu.BackEnd.controller;

import com.luu.BackEnd.exception.UserAlreadyExistsException;
import com.luu.BackEnd.payload.request.AuthRequest;
import com.luu.BackEnd.payload.request.RegisterRequest;
import com.luu.BackEnd.payload.response.ResponseData;
import com.luu.BackEnd.service.AuthService;
import com.luu.BackEnd.service.UserService;
import com.luu.BackEnd.utils.JwtUtilsHelper;
import com.luu.BackEnd.validation.CaptchaValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
//    @Autowired
//    private UserService userService;
    @Autowired
    private CaptchaValidator captchaValidator;
    @Autowired
    private JwtUtilsHelper jwtUtilsHelper;

    @Autowired
    private AuthService authService;
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

        // Kiểm tra reCAPTCHA
//        if (!captchaValidator.validateCaptcha(registerRequest.getCaptchaResponse())) {
//            return ResponseEntity.badRequest().body(new ResponseData(false, "Invalid reCAPTCHA", null));
//        }
        try {
            // Gọi service để đăng ký người dùng
            authService.registerCustomer(registerRequest);
            return ResponseEntity.ok(new ResponseData(true, "User registered successfully", registerRequest));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(new ResponseData(false, e.getMessage(), null));
        } catch (Exception e) {
            // Bắt mọi ngoại lệ khác
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(false, "An error occurred: " + e.getMessage(), null));
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest, BindingResult bindingResult) {
        ResponseData responseData = new ResponseData();
        try {
            String jwt = authService.authenticateUser(authRequest);
            if (jwt != null) {
                responseData.setSuccess(true);
                responseData.setData(jwt);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                StringBuilder errorMessage = new StringBuilder();
                bindingResult.getFieldErrors().forEach(error -> {
                    errorMessage.append(error.getDefaultMessage()).append("; ");
                });
                responseData.setSuccess(false);
                responseData.setMessage(errorMessage.toString());
                return new ResponseEntity<>(responseData, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setMessage("An error occurred during login");
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
