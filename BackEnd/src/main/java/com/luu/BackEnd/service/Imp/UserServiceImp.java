package com.luu.BackEnd.service.Imp;

import com.luu.BackEnd.payload.request.RegisterRequest;

public interface UserServiceImp {
    boolean  registerCustomer(RegisterRequest registerRequest);
    boolean checkLogin(String username, String password);

}
