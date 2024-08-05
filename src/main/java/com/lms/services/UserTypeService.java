package com.lms.services;

import com.lms.dto.UserTypeDto;
import com.lms.response.UserTypeResponse;

public interface UserTypeService {
    UserTypeResponse createUserType(UserTypeDto userTypeDto);
    UserTypeResponse updateUserType(UserTypeDto userTypeDto);
    UserTypeResponse getAllUserTypes();
    UserTypeResponse getSingleUserType(Long id);
    UserTypeResponse deleteUserType(Long id);

}
