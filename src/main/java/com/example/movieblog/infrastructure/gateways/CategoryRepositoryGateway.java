package com.example.movieblog.infrastructure.gateways;

import com.example.movieblog.application.gateways.CategoryGateway;
import com.example.movieblog.domain.entities.Category;
import com.example.movieblog.infrastructure.gateways.mappers.CategoryEntityMapper;
import com.example.movieblog.infrastructure.persistence.entities.CategoryEntity;
import com.example.movieblog.infrastructure.persistence.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

public class CategoryRepositoryGateway implements CategoryGateway {
    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    public CategoryRepositoryGateway(CategoryRepository categoryRepository, CategoryEntityMapper categoryEntityMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;
    }

    @Override
    public Category createCategory(Category category) {
        CategoryEntity categoryEntity = categoryEntityMapper.toEntity(category);
        CategoryEntity createdCategory = categoryRepository.save(categoryEntity);
        return categoryEntityMapper.toDomainObj(createdCategory);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll().stream().map(categoryEntityMapper::toDomainObj).toList();
    }

    @Override
    public Optional<Category> findOne(Long id) {
        return categoryRepository.findById(id)
                .map(categoryEntityMapper::toDomainObj);
    }

    @Override
    public Category update(Category category) {
        CategoryEntity categoryEntity = categoryEntityMapper.toEntity(category);
        CategoryEntity updatedCategory = categoryRepository.save(categoryEntity);
        return categoryEntityMapper.toDomainObj(updatedCategory);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
