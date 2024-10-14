package com.luu.BackEnd.service;

import com.luu.BackEnd.payload.request.AuthRequest;
import com.luu.BackEnd.payload.request.RegisterRequest;

public interface AuthService {
    String authenticateUser(AuthRequest authRequest);
    boolean  registerCustomer(RegisterRequest registerRequest);
}
