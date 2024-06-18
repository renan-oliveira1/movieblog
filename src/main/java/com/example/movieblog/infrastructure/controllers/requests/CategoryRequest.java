package com.example.movieblog.infrastructure.controllers.requests;

import org.springframework.lang.NonNull;

public record CategoryRequest(@NonNull String name, @NonNull String description) {
}
