package com.lms.services.impl;

import com.lms.dto.UserDto;
import com.lms.dto.UserTypeDto;
import com.lms.entitiy.User;
import com.lms.entitiy.UserType;
import com.lms.helper.ModelMapper;
import com.lms.repositories.UserTypeRepo;
import com.lms.response.UserResponse;
import com.lms.response.UserTypeResponse;
import com.lms.services.UserTypeService;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserTypeServiceImpl implements UserTypeService {

    private UserTypeRepo userTypeRepo;

    public UserTypeServiceImpl(UserTypeRepo userTypeRepo) {
        this.userTypeRepo = userTypeRepo;
    }

    @Override
    public UserTypeResponse createUserType(UserTypeDto userTypeDto) {
        UserTypeResponse res = new UserTypeResponse();
        try {

            // Check for duplicate email
            if (userTypeRepo.existsByName(userTypeDto.getName())) {
                res.setSuccess(false);
                res.setMessage("A user type with this name already exists.");
                return res;
            }

            // Check for duplicate mobile number


            // Convert UserDto to User entity
            UserType userType = ModelMapper.userTypeDtoToEntity(userTypeDto);
            userType.setEntryAt(new Date());
            // Save the user entity
            UserType savedUserType = userTypeRepo.save(userType);

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
    public UserTypeResponse updateUserType(UserTypeDto userTypeDto) {
        UserTypeResponse res = new UserTypeResponse();

        try {
            Optional<UserType> existingUserTypeOptional = userTypeRepo.findById(userTypeDto.getId());
            if(!existingUserTypeOptional.isPresent()){
                res.setSuccess(false);
                res.setMessage("User Type not found.");
                return res;
            }

            UserType existingUserType = existingUserTypeOptional.get();

            if(userTypeRepo.existsByNameAndIdNot(userTypeDto.getName(), userTypeDto.getId())){
                res.setSuccess(false);
                res.setMessage("A user type with this name already exists. ");
                return res;
            }

            existingUserType.setId(userTypeDto.getId());
            existingUserType.setName(userTypeDto.getName());
            existingUserType.setPermissions(ModelMapper.formatPermissions(userTypeDto.getPermissions()));

            UserType updatedUserType = userTypeRepo.save(existingUserType);

            res.setSuccess(true);
            res.setMessage("User type updated successfully.");
        } catch (DataAccessException e){
            res.setSuccess(false);
            res.setMessage("Database error occurred: " + e.getMessage());
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMessage("An error occurred: " + e.getMessage());
        }

        return res;

    }

    @Override
    public UserTypeResponse getAllUserTypes() {
        UserTypeResponse res = new UserTypeResponse();
        try {
            // Fetch all user types from the repository
            List<UserType> userTypes = userTypeRepo.findAll();

            // Check if any user types are found
            if (userTypes.isEmpty()) {
                res.setSuccess(true);
                res.setMessage("No user types found.");
                res.setResult(new ArrayList<>()); // Assuming you have a list field in UserTypeResponse
            } else {
                List<UserTypeDto> userTypeDtos = userTypes.stream()
                                .map(ModelMapper::userTypeEntityToDto)
                                .collect(Collectors.toList());
                res.setSuccess(true);
                res.setMessage("User types retrieved successfully.");
                res.setResult(userTypeDtos); // Assuming you have a list field in UserTypeResponse
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
    public UserTypeResponse getSingleUserType(Long id) {
        UserTypeResponse res = new UserTypeResponse();
        try {
            // Fetch the user type by ID from the repository
            Optional<UserType> userTypeOptional = userTypeRepo.findById(id);

            // Check if the user type exists
            if (userTypeOptional.isPresent()) {
                UserType userType = userTypeOptional.get();
                UserTypeDto userTypeDto = ModelMapper.userTypeEntityToDto(userType);

                List<UserTypeDto> result = new ArrayList<>();
                result.add(userTypeDto);

                res.setSuccess(true);
                res.setMessage("User type retrieved successfully.");

                res.setResult(result); // Assuming you have a field to hold a single UserType in UserTypeResponse
            } else {
                res.setSuccess(false);
                res.setMessage("User type not found.");
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
    public UserTypeResponse deleteUserType(Long id) {
        UserTypeResponse res = new UserTypeResponse();
        try {
            // Check if the user type exists
            Optional<UserType> userTypeOptional = userTypeRepo.findById(id);
            if (userTypeOptional.isPresent()) {
                // Delete the user type
                userTypeRepo.deleteById(id);
                res.setSuccess(true);
                res.setMessage("User type deleted successfully.");
            } else {
                res.setSuccess(false);
                res.setMessage("User type not found.");
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
