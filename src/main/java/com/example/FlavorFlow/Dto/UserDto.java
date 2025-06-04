package com.example.FlavorFlow.Dto;

import com.example.FlavorFlow.Enum.Roles;
import com.example.FlavorFlow.Model.Role;
import jakarta.persistence.Column;

import java.util.HashSet;
import java.util.Set;

public class UserDto {

    private String firstName;
    private String lastName;
    private String username;
    private Roles role;

    public UserDto(String firstName, String lastName, String username, Roles role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.role = role;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
