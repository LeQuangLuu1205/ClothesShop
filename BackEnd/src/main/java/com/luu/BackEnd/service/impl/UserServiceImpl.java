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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserStatusRepository userStatusRepository;
    public boolean registerCustomer(RegisterRequest request) throws UserAlreadyExistsException {
        // Check if email already exists
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Password encryption
        user.setFullname(request.getFullname());
        user.setPhoneNumber(request.getPhoneNumber());

        // Role is Customer and StatusUser is Active
        Role customerRole = roleRepository.findByRoleName("CUSTOMER");
//        Role customerRole = roleRepository.findByRoleName("EMPLOYEE");
//        Role customerRole = roleRepository.findByRoleName("ADMIN");
        UserStatus activeStatus = userStatusRepository.findByStatusName("ACTIVE");
        user.setRoles(customerRole);
        user.setUserStatus(activeStatus);

        userRepository.save(user); // Save user's information into database
        return true; // register successfully
    }

    @Override
    public boolean checkLogin(String username, String password) {
        User user =  userRepository.findByUserName(username);
        return passwordEncoder.matches(password,user.getPassword());
    }
}
