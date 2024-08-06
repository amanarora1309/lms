package com.lms.helper;

import com.lms.dto.UserDto;
import com.lms.dto.UserTypeDto;
import com.lms.entitiy.User;
import com.lms.entitiy.UserType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModelMapper {
    public static UserTypeDto userTypeEntityToDto(UserType userType) {
        if (userType == null) {
            return null;
        }

        UserTypeDto dto = new UserTypeDto();
        dto.setId(userType.getId());
        dto.setName(userType.getName());
        dto.setPermissions(parsePermissions(userType.getPermissions()));

        return dto;
    }

    public static List<String> parsePermissions(String permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(permissions.split(","));
    }


    public static UserType userTypeDtoToEntity(UserTypeDto dto) {
        if (dto == null) {
            return null;
        }

        UserType userType = new UserType();
        userType.setId(dto.getId());
        userType.setName(dto.getName());
        userType.setPermissions(formatPermissions(dto.getPermissions()));

        return userType;
    }

    public static String formatPermissions(List<String> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return "";
        }
        return String.join(",", permissions);
    }


    public static UserDto UserEntityToDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setMobile(user.getMobile());
        dto.setPassword(user.getPassword());
        dto.setUserTypeDto(userTypeEntityToDto(user.getUserType()));

        return dto;
    }


    public static User UserDtoToEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setMobile(dto.getMobile());
        user.setPassword(dto.getPassword());
        user.setUserType(userTypeDtoToEntity(dto.getUserTypeDto()));
        return user;
    }
}
