package com.example.user.domain.dto;

import com.example.user.domain.entity.User;

public class LoginResponse {
    private String name;
    private String profileImgPath;

    public LoginResponse(String name, String profile_img_path) {
        this.name = name;
        this.profileImgPath = profile_img_path;
    }

    public String getName() {
        return name;
    }

    public String getProfile_img_path() {
        return profileImgPath;
    }

    public LoginResponse(User user) {
        this.name = user.getName();
        this.profileImgPath =getProfile_img_path();
    }
}