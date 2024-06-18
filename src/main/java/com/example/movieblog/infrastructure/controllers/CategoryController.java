package com.example.movieblog.infrastructure.controllers;

import com.example.movieblog.application.usecases.category.*;
import com.example.movieblog.domain.entities.Category;
import com.example.movieblog.infrastructure.controllers.mapper.CategoryDtoMapper;
import com.example.movieblog.infrastructure.controllers.requests.CategoryRequest;
import com.example.movieblog.infrastructure.controllers.responses.CategoryResponse;
import com.example.movieblog.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {
    private final CreateCategoryInteractor createCategoryInteractor;
    private final GetAllCategoriesInteractor getAllCategoriesInteractor;
    private final GetCategoryInteractor getCategoryInteractor;
    private final UpdateCategoryInteractor updateCategoryInteractor;
    private final DeleteCategoryInteractor deleteCategoryInteractor;
    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryController(CreateCategoryInteractor createCategoryInteractor, GetAllCategoriesInteractor getAllCategoriesInteractor,
                              GetCategoryInteractor getCategoryInteractor, UpdateCategoryInteractor updateCategoryInteractor,
                              DeleteCategoryInteractor deleteCategoryInteractor, CategoryDtoMapper categoryDtoMapper) {
        this.createCategoryInteractor = createCategoryInteractor;
        this.getAllCategoriesInteractor = getAllCategoriesInteractor;
        this.getCategoryInteractor = getCategoryInteractor;
        this.updateCategoryInteractor = updateCategoryInteractor;
        this.deleteCategoryInteractor = deleteCategoryInteractor;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    @PostMapping("/categories")
    public ResponseEntity<Object> save(@RequestBody @Validated CategoryRequest categoryRequest){
        Category category = categoryDtoMapper.toCategory(categoryRequest);
        Category createdCategory = createCategoryInteractor.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> findAll(){
        List<CategoryResponse> categories = getAllCategoriesInteractor.getAllCategories().stream().map(categoryDtoMapper::toResponse).toList();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Object> findOne(@PathVariable(value = "id") Long id){
        Optional<Category> optionalCategory = getCategoryInteractor.getCategory(id);
        if(optionalCategory.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found!!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(categoryDtoMapper.toResponse(optionalCategory.get()));
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Validated CategoryRequest categoryRequest){
        Optional<Category> updatedCategory = updateCategoryInteractor.updateCategory(id, categoryDtoMapper.toCategory(categoryRequest));
        if(updatedCategory.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Some thing gone wrong!!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(categoryDtoMapper.toResponse(updatedCategory.get()));
    }

    @DeleteMapping("/categoires/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id){
        deleteCategoryInteractor.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body("Category deleted!!");
    }


}
