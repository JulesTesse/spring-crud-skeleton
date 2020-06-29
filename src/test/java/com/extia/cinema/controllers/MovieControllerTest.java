package com.extia.cinema.controllers;

import com.extia.cinema.CinemaApplication;
import com.extia.cinema.datas.Category;
import com.extia.cinema.datas.Movie;
import com.extia.cinema.repositories.ICategoryRepository;
import com.extia.cinema.repositories.IMovieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CinemaApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class MovieControllerTest {

    private static final String URL = "/api/v1/movies";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IMovieRepository movieRepository;

    @Test
    public void findAllTest() throws Exception {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(1L, "movie1", 130, new Category(1L, "category1")));
        movieList.add(new Movie(2L, "movie2", 150, new Category(1L, "category1")));
        movieList.add(new Movie(3L, "movie3", 60, new Category(2L, "category2")));


        when(movieRepository.findAll()).thenReturn(movieList);
        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void findOneByIdTest() throws Exception {
        Movie movie = new Movie(1L, "movie1", 130, new Category(1L, "toto"));

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(movieRepository.findById(2L)).thenReturn(Optional.empty());
        mockMvc.perform(get(URL + "/1"))
                .andDo(print())
                .andExpect(status().isOk());


        mockMvc.perform(get(URL + "/2"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void createTest() throws Exception {
        Category category = new Category(1L, "category");
        Movie movie = new Movie();
        movie.setName("tata");
        movie.setDuration(111);
        movie.setCategory(category);

        Movie movieCreated = new Movie(1L, "tata", 111, new Category(1L, "toto"));

        when(movieRepository.save(movie)).thenReturn(movieCreated);
        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(movie)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void updateTest() throws Exception {
        Category category = new Category(1L,"tata");
        Movie movie = new Movie(1L, "titi", 200, category);

        when(movieRepository.save(any())).thenReturn(movie);
        mockMvc.perform(patch(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(movie)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
