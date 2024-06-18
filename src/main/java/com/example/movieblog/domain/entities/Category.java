package com.example.movieblog.domain.entities;

public record Category(Long id, String name, String description) {
    public Category(String name, String description) {
        this(null, name, description);
    }
}
