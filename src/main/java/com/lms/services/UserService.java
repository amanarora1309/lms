package com.lms.services;

import com.lms.dto.UserDto;
import com.lms.response.LoginResponse;
import com.lms.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserResponse createUser(UserDto userDto);
    UserResponse updateUser(UserDto userDto);
    UserResponse getAllUsers();
    UserResponse getSingleUser(Long id);
    UserResponse deleteUser(Long id);

    LoginResponse login (UserDto userDto);
}
