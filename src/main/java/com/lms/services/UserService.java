package com.lms.services;

import com.lms.dto.UserDto;
import com.lms.response.UserResponse;

public interface UserService {
    UserResponse createUser(UserDto userDto);
    UserResponse updateUser(UserDto userDto);
    UserResponse getAllUsers();
    UserResponse getSingleUser(Long id);
    UserResponse deleteUser(Long id);

}
