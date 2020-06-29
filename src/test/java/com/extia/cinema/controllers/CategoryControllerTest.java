package com.extia.cinema.controllers;

import com.extia.cinema.CinemaApplication;
import com.extia.cinema.datas.Category;
import com.extia.cinema.repositories.ICategoryRepository;
import com.extia.cinema.repositories.IMovieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
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
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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


    @Test
    public void findAllTest() throws Exception {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1L, "category1"));
        categoryList.add(new Category(2L, "category2"));

        when(categoryRepository.findAll()).thenReturn(categoryList);
        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andDo(print());
//                .andReturn();
    }

    @Test
    public void findOneByIdTest() throws Exception {
        Category category = new Category(1L, "toto");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.findById(2L)).thenReturn(Optional.empty());
        mockMvc.perform(get(URL + "/1"))
                .andDo(print())
                .andExpect(status().isOk());


        mockMvc.perform(get(URL + "/2"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void createTest() throws Exception {
        Category category = new Category();
        category.setName("toto");

        Category categoryCreated = new Category(1L, "toto");

        when(categoryRepository.findByName(category.getName())).thenReturn(null);
        when(categoryRepository.save(category)).thenReturn(categoryCreated);
        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(category)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void updateTest() throws Exception {
        Category category = new Category(1L,"tata");

        when(categoryRepository.save(any())).thenReturn(category);
        mockMvc.perform(patch(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(category)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
