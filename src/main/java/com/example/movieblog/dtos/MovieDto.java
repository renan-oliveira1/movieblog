package com.example.movieblog.dtos;

import org.springframework.lang.NonNull;

import java.util.List;


public record MovieDto(@NonNull String title, @NonNull String description, @NonNull String premiere, @NonNull
                       List<CategoryMovieDto> categories) {
}
