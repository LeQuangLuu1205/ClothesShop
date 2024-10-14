package com.luu.BackEnd.service.impl;

import com.luu.BackEnd.entity.Role;
import com.luu.BackEnd.entity.User;
import com.luu.BackEnd.entity.UserStatus;
import com.luu.BackEnd.exception.UserAlreadyExistsException;
import com.luu.BackEnd.payload.request.RegisterRequest;
import com.luu.BackEnd.reponsitory.RoleRepository;
import com.luu.BackEnd.reponsitory.UserRepository;
import com.luu.BackEnd.reponsitory.UserStatusRepository;
import com.luu.BackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
}
