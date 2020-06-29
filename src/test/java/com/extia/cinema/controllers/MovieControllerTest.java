package com.extia.cinema.controllers;

import com.extia.cinema.CinemaApplication;
import com.extia.cinema.repositories.ICategoryRepository;
import com.extia.cinema.repositories.IMovieRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

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
}
