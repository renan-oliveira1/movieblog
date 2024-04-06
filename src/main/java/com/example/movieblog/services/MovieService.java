package com.example.movieblog.services;

import com.example.movieblog.models.MovieModel;
import com.example.movieblog.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieService implements IService<UUID, MovieModel> {

    @Autowired
    private MovieRepository  movieRepository;

    @Transactional
    @Override
    public MovieModel save(MovieModel movieModel) {
        return movieRepository.save(movieModel);
    }

    @Override
    public List<MovieModel> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<MovieModel> findOne(UUID id) {
        return movieRepository.findById(id);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        movieRepository.deleteById(id);
    }
}
