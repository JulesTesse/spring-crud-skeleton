package com.extia.cinema.controllers;

import com.extia.cinema.CinemaApplication;
import com.extia.cinema.datas.Category;
import com.extia.cinema.repositories.ICategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@SpringBootTest(
//        SpringBootTest.WebEnvironment.MOCK,
//        classes = CinemaApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class CategoryControllerTest {

    private static final String URL = "/api/v1/categories";

   @Autowired
    private MockMvc mockMvc;

   @MockBean
    private ICategoryRepository repository;

   @Test
    public void whenFindAll_thenReturnAllCat() throws Exception{
       mockMvc.perform(post(URL)
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andDo(print());
   }
}
