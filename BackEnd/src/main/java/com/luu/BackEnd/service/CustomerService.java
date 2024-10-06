package com.luu.BackEnd.service;

import com.luu.BackEnd.entity.Role;
import com.luu.BackEnd.entity.User;
import com.luu.BackEnd.entity.UserStatus;
import com.luu.BackEnd.exception.UserAlreadyExistsException;
import com.luu.BackEnd.payload.request.RegisterRequest;
import com.luu.BackEnd.reponsitory.RoleRepository;
import com.luu.BackEnd.reponsitory.UserRepository;
import com.luu.BackEnd.reponsitory.UserStatusRepository;
import com.luu.BackEnd.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements UserServiceImp {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserStatusRepository userStatusRepository;
    public boolean registerCustomer(RegisterRequest request) throws UserAlreadyExistsException{
            // Check if email already exists
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new UserAlreadyExistsException("Email already exists");
            }

            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword())); // Password encryption
            user.setFullname(request.getFullname());
            user.setPhoneNumber(request.getPhoneNumber());

            // Role is Customer and StatusUser is Active
            Role customerRole = roleRepository.findByRoleName("CUSTOMER");
            UserStatus activeStatus = userStatusRepository.findByStatusName("ACTIVE");
            user.setRoles(customerRole);
            user.setUserStatus(activeStatus);

            userRepository.save(user); // Save user's information into database
            return true; // register successfully
    }
}
