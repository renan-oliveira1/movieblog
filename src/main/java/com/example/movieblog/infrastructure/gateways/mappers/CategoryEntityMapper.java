package com.example.movieblog.infrastructure.gateways.mappers;

import com.example.movieblog.domain.entities.Category;
import com.example.movieblog.infrastructure.persistence.entities.CategoryEntity;

public class CategoryEntityMapper {
    public CategoryEntity toEntity(Category category){
        return new CategoryEntity(category.id(), category.name(), category.description());
    }

    public Category toDomainObj(CategoryEntity categoryEntity){
        return new Category(categoryEntity.getId(), categoryEntity.getName(), categoryEntity.getDescription());
    }
}
