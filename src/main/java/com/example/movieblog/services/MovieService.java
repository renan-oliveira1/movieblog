package com.example.movieblog.services;

import com.example.movieblog.models.Movie;
import com.example.movieblog.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieService implements IService<UUID, Movie> {

    @Autowired
    MovieRepository  movieRepository;

    @Transactional
    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findOne(UUID id) {
        return movieRepository.findById(id);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        movieRepository.deleteById(id);
    }
}
