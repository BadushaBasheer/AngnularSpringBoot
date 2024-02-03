package com.backend.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_details")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

//    @Column(name = "first_name")
    private String fName;

//    @Column(name = "last_name")
    private String lName;

//    @Column(name = "gender")
    private String gender;

    @Email
    private String email;

//    @Column(name = "user_password")
    private String password;

//    @Column(name = "active")
    private boolean isEnabled;

//    private String image;
}
