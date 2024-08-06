package com.lms.services.impl;

import com.lms.dto.UserDto;
import com.lms.entitiy.User;
import com.lms.helper.ModelMapper;
import com.lms.repositories.UserRepo;
import com.lms.response.UserResponse;
import com.lms.services.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {


    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserResponse createUser(UserDto userDto) {

        UserResponse res = new UserResponse();
        try {

            // Check for duplicate email
            if (userRepo.existsByEmail(userDto.getEmail())) {
                res.setSuccess(false);
                res.setMessage("A user with this email already exists.");
                return res;
            }

            // Check for duplicate mobile number
            if (userRepo.existsByMobile(userDto.getMobile())) {
                res.setSuccess(false);
                res.setMessage("A user with this mobile number already exists.");
                return res;
            }

            // Convert UserDto to User entity
            User user = ModelMapper.UserDtoToEntity(userDto);
            user.setEntryAt(new Date());
            // Save the user entity
            User savedUser = userRepo.save(user);

            // Prepare the success response
            res.setSuccess(true);
            res.setMessage("User Created Successfully");
        } catch (DataAccessException e) {
            // Handle database exceptions
            res.setSuccess(false);
            res.setMessage("Database error occurred: " + e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            res.setSuccess(false);
            res.setMessage("An error occurred: " + e.getMessage());
        }

        return res;
    }


    @Override
    public UserResponse updateUser(UserDto userDto) {
        UserResponse res = new UserResponse();
        try {
            // Fetch the existing user by id
            Optional<User> existingUserOptional = userRepo.findById(userDto.getId());
            if (existingUserOptional.isPresent()) {
                User existingUser = existingUserOptional.get();

                // Check for duplicate email (excluding the current user's email)
                if (userRepo.existsByEmail(userDto.getEmail()) && !existingUser.getEmail().equals(userDto.getEmail())) {
                    res.setSuccess(false);
                    res.setMessage("A user with this email already exists.");
                    return res;
                }

                // Check for duplicate mobile number (excluding the current user's mobile)
                if (userRepo.existsByMobile(userDto.getMobile()) && !existingUser.getMobile().equals(userDto.getMobile())) {
                    res.setSuccess(false);
                    res.setMessage("A user with this mobile number already exists.");
                    return res;
                }

                // Update the existing user with new values from userDto
                existingUser.setName(userDto.getName());
                existingUser.setEmail(userDto.getEmail());
                existingUser.setMobile(userDto.getMobile());
                existingUser.setPassword(userDto.getPassword());
                existingUser.setUpdatedAt(new Date());

                // Assuming you have a method to convert UserTypeDto to UserType entity
                existingUser.setUserType(ModelMapper.userTypeDtoToEntity(userDto.getUserTypeDto()));

                // Save the updated user entity
                User updatedUser = userRepo.save(existingUser);

                // Prepare the success response
                res.setSuccess(true);
                res.setMessage("User Updated Successfully");
            } else {
                // Handle the case where the user is not found
                res.setSuccess(false);
                res.setMessage("User not found with id: " + userDto.getId());
            }
        } catch (DataAccessException e) {
            // Handle database exceptions
            res.setSuccess(false);
            res.setMessage("Database error occurred: " + e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            res.setSuccess(false);
            res.setMessage("An error occurred: " + e.getMessage());
        }

        return res;
    }

    @Override
    public UserResponse getAllUsers() {
        UserResponse res  = new UserResponse();

        try {
            List<User> users = userRepo.findAll();
            List<UserDto> userDtos = users.stream()
                    .map(ModelMapper::UserEntityToDto)
                    .collect(Collectors.toList());

            res.setSuccess(true);
            res.setMessage("User Fetch Successfully");
            res.setResult(userDtos);
        } catch (DataAccessException e){
            res.setSuccess(false);
            res.setMessage("Database error occured " + e.getMessage());
        } catch (Exception e){
            res.setSuccess(false);
            res.setMessage("An error occured " + e.getMessage());
        }

        return res;
    }

    @Override
    public UserResponse getSingleUser(Long id) {
        UserResponse res = new UserResponse();
        try {
            Optional<User> userOptional = userRepo.findById(id);
            if(userOptional.isPresent()){
                User user = userOptional.get();

                UserDto userDto = ModelMapper.UserEntityToDto(user);
                List<UserDto> result = new ArrayList<>();
                result.add(userDto);
                res.setSuccess(true);
                res.setMessage("User Found Successfully");
                res.setResult(result);
            }
            else{
                res.setSuccess(false);
                res.setMessage("User not found with id: " + id);
            }
        } catch (DataAccessException e) {
            // Handle database exceptions
            res.setSuccess(false);
            res.setMessage("Database error occurred: " + e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            res.setSuccess(false);
            res.setMessage("An error occurred: " + e.getMessage());
        }
        return res;
    }

    @Override
    public UserResponse deleteUser(Long id) {
        UserResponse res = new UserResponse();
        try {
            // Fetch the user by id
            Optional<User> userOptional = userRepo.findById(id);
            if (userOptional.isPresent()) {
                // Delete the user entity
                userRepo.deleteById(id);

                // Prepare the success response
                res.setSuccess(true);
                res.setMessage("User deleted successfully");
            } else {
                // Handle the case where the user is not found
                res.setSuccess(false);
                res.setMessage("User not found with id: " + id);
            }
        } catch (DataAccessException e) {
            // Handle database exceptions
            res.setSuccess(false);
            res.setMessage("Database error occurred: " + e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            res.setSuccess(false);
            res.setMessage("An error occurred: " + e.getMessage());
        }

        return res;
    }
}
