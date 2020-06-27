package com.extia.cinema.repositories;

import com.extia.cinema.datas.Category;
import org.springframework.data.jpa.repository.Query;

public interface ICategoryRepository extends IGenericRepository<Category, Integer> {

    @Query("SELECT c FROM Category c WHERE c.name=?1")
    Category findByName(String name);
}
