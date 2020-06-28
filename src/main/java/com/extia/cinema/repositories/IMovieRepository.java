package com.extia.cinema.repositories;

import com.extia.cinema.datas.Movie;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRepository extends IGenericRepository<Movie, Long> {
}
