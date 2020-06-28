package com.extia.cinema.repositories;

import com.extia.cinema.datas.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Test
    public void whenFindByName_thenReturnCat(){
        Category category = new Category();
        category.setName("toto");

        Category found = categoryRepository.findByName(category.getName());

        assertThat(found.getName()).isEqualTo(category.getName());
    }
}
