package com.extia.cinema.services.impl;

import com.extia.cinema.datas.Movie;
import com.extia.cinema.exceptions.NotFoundException;
import com.extia.cinema.repositories.IMovieRepository;
import com.extia.cinema.services.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IMovieService {

    @Autowired
    private IMovieRepository movieRepository;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Integer id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isEmpty()) {
            throw new NotFoundException("Movie with id " + id + " not found");
        }
        return optionalMovie.get();
    }

    @Override
    public Movie create(Movie movie) {
        this.checkAndFormatMovie(movie);
        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Movie movie) {
        this.checkAndFormatMovie(movie);
        return movieRepository.save(movie);
    }

    @Override
    public void delete(Integer id) {
        Movie toDelete = this.findById(id);
        this.movieRepository.delete(toDelete);
    }

    private void checkAndFormatMovie(Movie movie) {
//        Here do all necessaries checks to create or update movie
        movie.setName(this.formatName(movie.getName()));
    }

    private String formatName(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}
