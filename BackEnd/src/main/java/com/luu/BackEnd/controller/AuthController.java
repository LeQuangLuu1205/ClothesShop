package com.luu.BackEnd.controller;

import com.luu.BackEnd.exception.UserAlreadyExistsException;
import com.luu.BackEnd.payload.request.RegisterRequest;
import com.luu.BackEnd.payload.response.ResponseData;
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
    @Autowired
    private UserService userService;
    @Autowired
    private CaptchaValidator captchaValidator;
    @Autowired
    private JwtUtilsHelper jwtUtilsHelper;
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
            userService.registerCustomer(registerRequest);
            return ResponseEntity.ok(new ResponseData(true, "User registered successfully", registerRequest));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(new ResponseData(false, e.getMessage(), null));
        } catch (Exception e) {
            // Bắt mọi ngoại lệ khác
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(false, "An error occurred: " + e.getMessage(), null));
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password) {
        ResponseData responseData = new ResponseData();
        if (userService.checkLogin(username, password)){
            String token = jwtUtilsHelper.generateToken(username);
            responseData.setSuccess(true);
            responseData.setData(token);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            responseData.setSuccess(false);
            responseData.setMessage("Invalid username or password");
            return new ResponseEntity<>(responseData, HttpStatus.UNAUTHORIZED);
        }
    }
}
