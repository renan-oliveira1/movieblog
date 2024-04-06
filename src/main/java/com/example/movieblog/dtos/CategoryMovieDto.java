package com.example.movieblog.dtos;

import org.springframework.lang.NonNull;

public record CategoryMovieDto(@NonNull Long id, String name) {
}
