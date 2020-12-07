package com.myshop.demo.service;

import com.myshop.demo.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean save(UserDto userDto);
}
