package com.example.movieblog.controller;

import com.example.movieblog.dtos.CategoryMovieDto;
import com.example.movieblog.dtos.MovieDto;
import com.example.movieblog.models.CategoryModel;
import com.example.movieblog.models.MovieModel;
import com.example.movieblog.services.CategoryService;
import com.example.movieblog.services.MovieService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class MovieController {

    final MovieService movieService;
    final CategoryService categoryService;

    public MovieController(MovieService movieService, CategoryService categoryService) {
        this.movieService = movieService;
        this.categoryService = categoryService;
    }

    @PostMapping("/movies")
    public ResponseEntity<Object> saveMovie(@RequestBody @Validated MovieDto movieDto){
        var movieModel = new MovieModel();
        BeanUtils.copyProperties(movieDto, movieModel);
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate datePremiere = LocalDate.parse(movieDto.premiere(), formatter);
            movieModel.setPremiere(datePremiere);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Premiere date in the wrong format(yyyy-mm-dd)");
        }
        movieModel.setPosted(LocalDate.now());

        for (CategoryMovieDto category: movieDto.categories()) {
            Optional<CategoryModel> optionalCategoryModel = categoryService.findOne(category.id());
            if(optionalCategoryModel.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found!!");
            }
            movieModel.getCategories().add(optionalCategoryModel.get());
        }

        if(!movieDto.encodedImage().isEmpty()){
            try{
                movieModel.setImage(Base64.getDecoder().decode(movieDto.encodedImage()));
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error to decode image from request!");
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.save(movieModel));
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieModel>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(movieService.findAll());
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Object> findOne(@PathVariable(value = "id") UUID id){
        Optional<MovieModel> optionalMovie = movieService.findOne(id);
        if(optionalMovie.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie Not Found!!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalMovie.get());
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody @Validated MovieDto movieDto){
        Optional<MovieModel> optionalMovie = movieService.findOne(id);
        if(optionalMovie.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie Not Found!!");
        }
        var movieModel = new MovieModel();
        BeanUtils.copyProperties(movieDto, movieModel);
        movieModel.setId(id);
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate datePremiere = LocalDate.parse(movieDto.premiere(), formatter);
            movieModel.setPremiere(datePremiere);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Premiere date in the wrong format(yyyy-mm-dd)");
        }

        for (CategoryMovieDto category: movieDto.categories()) {
            Optional<CategoryModel> optionalCategoryModel = categoryService.findOne(category.id());
            if(optionalCategoryModel.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found!!");
            }
            movieModel.getCategories().add(optionalCategoryModel.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(movieService.save(movieModel));
    }

    @DeleteMapping("movies/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id){
        Optional<MovieModel> optionalMovie = movieService.findOne(id);
        if (optionalMovie.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found!!");
        }
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Movie deleted!!");
    }
}
