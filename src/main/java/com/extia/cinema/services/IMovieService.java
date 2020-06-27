package com.extia.cinema.services;

import com.extia.cinema.datas.Movie;

import java.util.List;

public interface IMovieService {
    List<Movie> findAll();
    Movie findById(Integer id);
    Movie create(Movie movie);
    Movie update(Movie movie);
    void delete(Integer id);
}
