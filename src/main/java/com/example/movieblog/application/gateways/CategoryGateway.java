package com.example.movieblog.application.gateways;

import com.example.movieblog.domain.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryGateway {
    Category createCategory(Category category);
    List<Category> findAll();
    Optional<Category> findOne(Long id);
    Category update(Category category);
    void delete(Long id);
}
