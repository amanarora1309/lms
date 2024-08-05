package com.lms.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String mobile;
    private String password;

    private String entryBy;
    private Date entryAt;
    private String updatedBy;
    private Date updatedAt;
}
