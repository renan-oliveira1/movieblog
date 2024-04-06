package com.example.movieblog.dtos;

import org.springframework.lang.NonNull;

public record CategoryDto(@NonNull String name, @NonNull String description) {
}
