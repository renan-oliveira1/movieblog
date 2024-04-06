package com.example.movieblog.controller;

import com.example.movieblog.dtos.CategoryDto;
import com.example.movieblog.models.CategoryModel;
import com.example.movieblog.services.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ClientHttpResponseDecorator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {
    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public ResponseEntity<Object> save(@RequestBody @Validated CategoryDto categoryDto){
        var categoryModel = new CategoryModel();
        BeanUtils.copyProperties(categoryDto, categoryModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryModel));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryModel>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Object> findOne(@PathVariable(value = "id") Long id){
        Optional<CategoryModel> optionalCategoryModel = categoryService.findOne(id);
        if(optionalCategoryModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found!!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalCategoryModel.get());
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Validated CategoryDto categoryDto){
        Optional<CategoryModel> optionalCategoryModel = categoryService.findOne(id);
        if(optionalCategoryModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found!!");
        }
        var categoryModel = new CategoryModel();
        BeanUtils.copyProperties(categoryDto, categoryModel);
        categoryModel.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.save(categoryModel));
    }

    @DeleteMapping("/categoires/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id){
        Optional<CategoryModel> optionalCategoryModel = categoryService.findOne(id);
        if(optionalCategoryModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found!!");
        }
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Category deleted!!");
    }


}
