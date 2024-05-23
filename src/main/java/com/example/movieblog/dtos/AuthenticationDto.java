package com.example.movieblog.dtos;

import org.springframework.lang.NonNull;

public record AuthenticationDto(@NonNull String email, @NonNull String password){}
