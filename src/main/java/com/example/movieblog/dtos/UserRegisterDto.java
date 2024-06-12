package com.example.movieblog.dtos;

import com.example.movieblog.models.UserAuthority;
import org.springframework.lang.NonNull;

public record UserRegisterDto(String username, String email, String password, String phone, @NonNull UserAuthority authority) {
}
