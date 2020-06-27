package com.extia.cinema.controllers;

import com.extia.cinema.datas.Movie;
import com.extia.cinema.exceptions.BadRequestException;
import com.extia.cinema.exceptions.GlobalCinemaException;
import com.extia.cinema.exceptions.NotFoundException;
import com.extia.cinema.services.IMovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@Slf4j
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAll() {
        List<Movie> movieList = movieService.findAll();
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getOneById(@PathVariable("id") Integer id) {
        try {
            Movie movie = movieService.findById(id);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Movie not found",e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<Movie> create(@RequestBody @Validated Movie movie){
        try {
            Movie movieCreated = movieService.update(movie);
            return new ResponseEntity<>(movieCreated, HttpStatus.OK);
        }catch(BadRequestException e){
            log.error("Bad request when creating movie",e);
            throw e;
        }
    }

    @PatchMapping
    public ResponseEntity<Movie> update(@RequestBody @Validated Movie movie){
        try {
            Movie movieUpdated = movieService.update(movie);
            return new ResponseEntity<>(movieUpdated, HttpStatus.OK);
        }catch(BadRequestException e){
            log.error("Bad request when updating movie",e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
        try {
            movieService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(GlobalCinemaException e){
            log.error("Exception when deleting movie",e);
            throw e;
        }
    }
}
