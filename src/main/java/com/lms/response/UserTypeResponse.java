package com.lms.response;


import com.lms.entitiy.User;
import com.lms.entitiy.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTypeResponse {
    private boolean success;
    private String message;
    private List<UserType> result;
}
