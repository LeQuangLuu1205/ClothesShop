package com.luu.BackEnd.service.impl;

import com.luu.BackEnd.entity.Role;
import com.luu.BackEnd.entity.User;
import com.luu.BackEnd.entity.UserStatus;
import com.luu.BackEnd.exception.UserAlreadyExistsException;
import com.luu.BackEnd.payload.request.AuthRequest;
import com.luu.BackEnd.payload.request.RegisterRequest;
import com.luu.BackEnd.reponsitory.RoleRepository;
import com.luu.BackEnd.reponsitory.UserRepository;
import com.luu.BackEnd.reponsitory.UserStatusRepository;
import com.luu.BackEnd.service.AuthService;
import com.luu.BackEnd.utils.JwtUtilsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtilsHelper jwtUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserStatusRepository userStatusRepository;
    @Override
    public String authenticateUser(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtils.generateToken(userDetails);
    }

    public boolean registerCustomer(RegisterRequest request) throws UserAlreadyExistsException {
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullname(request.getFullname());
        user.setPhoneNumber(request.getPhoneNumber());

        // Role is Customer and StatusUser is Active
        Role customerRole = roleRepository.findByRoleName("CUSTOMER");
//        Role customerRole = roleRepository.findByRoleName("EMPLOYEE");
//        Role customerRole = roleRepository.findByRoleName("ADMIN");
        UserStatus activeStatus = userStatusRepository.findByStatusName("ACTIVE");
        user.setRoles(customerRole);
        user.setUserStatus(activeStatus);

        userRepository.save(user);
        return true;
    }

}
