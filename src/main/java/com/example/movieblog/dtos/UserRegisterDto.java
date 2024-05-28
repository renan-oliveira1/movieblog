package com.example.movieblog.dtos;

import com.example.movieblog.models.UserAuthority;

public record UserRegisterDto(String username, String email, String password, String phone, UserAuthority authority) {
}
