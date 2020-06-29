package com.extia.cinema.controllers;

import com.extia.cinema.datas.Category;
import com.extia.cinema.exceptions.BadRequestException;
import com.extia.cinema.exceptions.GlobalCinemaException;
import com.extia.cinema.exceptions.NotFoundException;
import com.extia.cinema.services.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@Slf4j
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categoryList = categoryService.findAll();
        return new ResponseEntity<>(categoryList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getOneById(@PathVariable("id") Long id) {
        try{
            Category category = categoryService.findById(id);
            return new ResponseEntity<>(category, HttpStatus.OK);
        }catch (NotFoundException e){
            log.error("Catgeory not found", e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody @Validated Category category){
        try {
            Category categoryCreated = categoryService.create(category);
            return new ResponseEntity<>(categoryCreated, HttpStatus.CREATED);
        }catch(BadRequestException e){
            log.error("Bad request when creating category",e);
            throw e;
        }
    }

    @PatchMapping
    public ResponseEntity<Category> update(@RequestBody @Validated Category category){
        try {
            Category categoryUpdated = categoryService.update(category);
            return new ResponseEntity<>(categoryUpdated, HttpStatus.OK);
        }catch(BadRequestException e){
            log.error("Bad request when updating category",e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        try {
            categoryService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(GlobalCinemaException e){
            log.error("Exception when deleting category",e);
            throw e;
        }
    }
}
