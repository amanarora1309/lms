package com.lms.dto;


import com.lms.entitiy.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserTypeDto {
    private Long id;
    private String name;
    private List<String> permissions;
    private String entryBy;
    private String entryAt;
    private String updatedBy;
    private String updatedAt;
}
