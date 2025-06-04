package com.example.FlavorFlow.Model;

import com.example.FlavorFlow.Dto.UserDto;

public class AuthResponse {
    private String jwt;
    private UserDto user;

    public AuthResponse(String jwt, UserDto user) {
        this.jwt = jwt;
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
