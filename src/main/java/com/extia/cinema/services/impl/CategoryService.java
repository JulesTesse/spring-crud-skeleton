package com.extia.cinema.services.impl;

import com.extia.cinema.datas.Category;
import com.extia.cinema.exceptions.BadRequestException;
import com.extia.cinema.exceptions.NotFoundException;
import com.extia.cinema.repositories.ICategoryRepository;
import com.extia.cinema.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {


    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new NotFoundException("Category with id " + id + " not found");
        }
        return optionalCategory.get();
    }

    @Override
    public Category create(Category category) {
        this.checkAndFormatCategory(category);
        if (alreadyExists(category)) {
            throw new BadRequestException("Category with name " + category.getName() + " already exists");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        this.checkAndFormatCategory(category);
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        Category toDelete = this.findById(id);
        categoryRepository.delete(toDelete);
    }

    private void checkAndFormatCategory(Category category) {
//        Here do all necessaries checks to create or update movie
        category.setName(this.formatName(category.getName()));
    }

    private String formatName(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    private boolean alreadyExists(Category category) {
        return categoryRepository.findByName(category.getName()) != null;
    }
}
