package com.luu.BackEnd.service;

import com.luu.BackEnd.payload.request.RegisterRequest;

public interface UserService {
    boolean  registerCustomer(RegisterRequest registerRequest);
    boolean checkLogin(String username, String password);
}
