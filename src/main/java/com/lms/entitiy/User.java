package com.lms.entitiy;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String mobile;
    private String password;
    @ManyToOne
    @JoinColumn(name = "userType_id")
    private UserType userType;
    private String entryBy;
    private Date entryAt;
    private String updatedBy;
    private Date updatedAt;
}
