package com.example.movieblog.main;

import com.example.movieblog.application.gateways.CategoryGateway;
import com.example.movieblog.application.usecases.category.*;
import com.example.movieblog.infrastructure.controllers.mapper.CategoryDtoMapper;
import com.example.movieblog.infrastructure.gateways.CategoryRepositoryGateway;
import com.example.movieblog.infrastructure.gateways.mappers.CategoryEntityMapper;
import com.example.movieblog.infrastructure.persistence.repositories.CategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryConfig {

    @Bean
    CreateCategoryInteractor createCategoryInteractor(CategoryGateway categoryGateway){
        return new CreateCategoryInteractor(categoryGateway);
    }

    @Bean
    GetAllCategoriesInteractor getAllCategoriesInteractor(CategoryGateway categoryGateway){
        return new GetAllCategoriesInteractor(categoryGateway);
    }

    @Bean
    GetCategoryInteractor getCategoryInteractor(CategoryGateway categoryGateway){
        return new GetCategoryInteractor(categoryGateway);
    }

    @Bean
    UpdateCategoryInteractor updateCategoryInteractor(CategoryGateway categoryGateway){
        return new UpdateCategoryInteractor(categoryGateway);
    }

    @Bean
    DeleteCategoryInteractor deleteCategoryInteractor(CategoryGateway categoryGateway){
        return new DeleteCategoryInteractor(categoryGateway);
    }

    @Bean
    CategoryGateway categoryGateway(CategoryRepository categoryRepository, CategoryEntityMapper categoryEntityMapper){
        return new CategoryRepositoryGateway(categoryRepository, categoryEntityMapper);
    }

    @Bean
    CategoryEntityMapper categoryEntityMapper(){
        return new CategoryEntityMapper();
    }

    @Bean
    CategoryDtoMapper categoryDtoMapper(){
        return new CategoryDtoMapper();
    }
}
