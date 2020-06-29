package com.extia.cinema.services;

import com.extia.cinema.datas.Category;
import com.extia.cinema.repositories.ICategoryRepository;
import com.extia.cinema.services.impl.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @InjectMocks
    CategoryService categoryService;

    @Mock
    @Autowired
    ICategoryRepository categoryRepository;

    @Test
    public void findAllTest(){
        List<Category> expectedCategories = new ArrayList<Category>();
        expectedCategories.add(new Category(1L, "category1"));
        expectedCategories.add(new Category(2L, "category2"));
        expectedCategories.add(new Category(3L, "category3"));

        ReflectionTestUtils.setField(categoryService, "categoryRepository", categoryRepository);
        when(categoryRepository.findAll()).thenReturn(expectedCategories);
        List<Category> actualCategories = categoryService.findAll();

        assertEquals(expectedCategories.size(), actualCategories.size());
        for (Category category : expectedCategories) {
            assertTrue(actualCategories.contains(category));
        }
    }


}
