package com.example.movieblog.dtos;

import org.springframework.lang.NonNull;


public record MovieDto(@NonNull String title, @NonNull String description, @NonNull String premiere) {
}
