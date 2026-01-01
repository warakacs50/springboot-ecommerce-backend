package com.kavinda.aicom.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import javax.management.relation.Role;
import java.util.Set;

@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "mandotory")
    private String name;

    @Email(message = "should be valid")
    @NotBlank(message = "mandotory")
    @Column(nullable = false,unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public User(){}

    public User(String name , String email , Role role){
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}
