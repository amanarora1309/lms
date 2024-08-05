package com.lms.services.impl;

import com.lms.dto.UserDto;
import com.lms.repositories.UserRepo;
import com.lms.response.UserResponse;
import com.lms.services.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {


    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserResponse createUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserResponse updateUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserResponse getAllUsers() {
        return null;
    }

    @Override
    public UserResponse getSingleUser(Long id) {
        return null;
    }

    @Override
    public UserResponse deleteUser(Long id) {
        return null;
    }
}
