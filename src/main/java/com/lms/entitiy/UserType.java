package com.lms.entitiy;


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
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String permissions;
    @OneToMany(mappedBy = "userType")
    private List<User> users;
    private String entryBy;
    private String entryAt;
    private String updatedBy;
    private String updatedAt;
}
