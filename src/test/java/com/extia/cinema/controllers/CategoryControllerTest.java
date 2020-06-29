package com.extia.cinema.controllers;

import com.extia.cinema.CinemaApplication;
import com.extia.cinema.datas.Category;
import com.extia.cinema.repositories.ICategoryRepository;
import com.extia.cinema.repositories.IMovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CinemaApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class CategoryControllerTest {

    private static final String URL = "/api/v1/categories";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryRepository categoryRepository;

    @MockBean
    private IMovieRepository movieRepository;

    @Test
    public void findAllTest() throws Exception{
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1L, "category1"));
        categoryList.add(new Category(2L, "category2"));

        when(categoryRepository.findAll()).thenReturn(categoryList);
        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andDo(print());
//                .andReturn();
    }
}
