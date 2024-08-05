package com.lms.helper;

import com.lms.dto.UserDto;
import com.lms.dto.UserTypeDto;
import com.lms.entitiy.User;
import com.lms.entitiy.UserType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class modelMapper {
    public static UserTypeDto toDto(UserType userType) {
        if (userType == null) {
            return null;
        }

        UserTypeDto dto = new UserTypeDto();
        dto.setId(userType.getId());
        dto.setName(userType.getName());
        dto.setPermissions(parsePermissions(userType.getPermissions()));
        dto.setEntryBy(userType.getEntryBy());
        dto.setEntryAt(userType.getEntryAt());
        dto.setUpdatedBy(userType.getUpdatedBy());
        dto.setUpdatedAt(userType.getUpdatedAt());

        return dto;
    }

    private static List<String> parsePermissions(String permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(permissions.split(","));
    }


    public static UserType toEntity(UserTypeDto dto) {
        if (dto == null) {
            return null;
        }

        UserType userType = new UserType();
        userType.setId(dto.getId());
        userType.setName(dto.getName());
        userType.setPermissions(formatPermissions(dto.getPermissions()));
        userType.setEntryBy(dto.getEntryBy());
        userType.setEntryAt(dto.getEntryAt());
        userType.setUpdatedBy(dto.getUpdatedBy());
        userType.setUpdatedAt(dto.getUpdatedAt());

        return userType;
    }

    private static String formatPermissions(List<String> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return "";
        }
        return String.join(",", permissions);
    }


    public static UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setMobile(user.getMobile());
        dto.setPassword(user.getPassword());
        dto.setEntryBy(user.getEntryBy());
        dto.setEntryAt(user.getEntryAt());
        dto.setUpdatedBy(user.getUpdatedBy());
        dto.setUpdatedAt(user.getUpdatedAt());

        return dto;
    }


    public static User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setMobile(dto.getMobile());
        user.setPassword(dto.getPassword());
        user.setEntryBy(dto.getEntryBy());
        user.setEntryAt(dto.getEntryAt());
        user.setUpdatedBy(dto.getUpdatedBy());
        user.setUpdatedAt(dto.getUpdatedAt());

        return user;
    }
}
