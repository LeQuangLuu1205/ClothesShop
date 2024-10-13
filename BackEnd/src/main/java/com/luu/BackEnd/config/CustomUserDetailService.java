package com.luu.BackEnd.config;

import com.luu.BackEnd.reponsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.luu.BackEnd.entity.User users = userRepository.findByUserName(username);
        if (users == null) {
            throw new UsernameNotFoundException("User doesn't exist");
        } else {
            return new User(username,users.getPassword(), users.getAuthorities());
        }
    }
}
