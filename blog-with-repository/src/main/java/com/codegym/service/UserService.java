package com.codegym.service;

import com.codegym.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService{
    User getUserByUsername(String email);
}
