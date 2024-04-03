package com.example.movieblog.controller;

import com.example.movieblog.dtos.MovieDto;
import com.example.movieblog.models.Movie;
import com.example.movieblog.services.MovieService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MovieController {

    final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/movies")
    public ResponseEntity<Object> saveMovie(@RequestBody @Validated MovieDto movieDto){
        var movieModel = new Movie();
        BeanUtils.copyProperties(movieDto, movieModel);
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate datePremiere = LocalDate.parse(movieDto.premiere(), formatter);
            movieModel.setPremiere(datePremiere);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Premiere date in the wrong format(yyyy-mm-dd)");
        }
        movieModel.setPosted(LocalDate.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.save(movieModel));
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(movieService.findAll());
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Object> findOne(@PathVariable(value = "id") UUID id){
        Optional<Movie> optionalMovie = movieService.findOne(id);
        if(optionalMovie.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie Not Found!!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalMovie.get());
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody @Validated MovieDto movieDto){
        Optional<Movie> optionalMovie = movieService.findOne(id);
        if(optionalMovie.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie Not Found!!");
        }
        var movieModel = new Movie();
        BeanUtils.copyProperties(movieDto, movieModel);
        movieModel.setId(id);
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate datePremiere = LocalDate.parse(movieDto.premiere(), formatter);
            movieModel.setPremiere(datePremiere);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Premiere date in the wrong format(yyyy-mm-dd)");
        }
        return ResponseEntity.status(HttpStatus.OK).body(movieService.save(movieModel));
    }

    @DeleteMapping("movies/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id){
        Optional<Movie> optionalMovie = movieService.findOne(id);
        if (optionalMovie.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found!!");
        }
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Movie deleted!!");
    }
}
