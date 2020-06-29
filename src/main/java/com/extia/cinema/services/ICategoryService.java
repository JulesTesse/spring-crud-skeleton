package com.extia.cinema.services;

import com.extia.cinema.datas.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> findAll();
    Category findById(Long id);
    Category create(Category category);
    Category update(Category category);
    void delete(Long id);
}
