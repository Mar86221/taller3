package org.luismore.taller3.services;



import org.luismore.taller3.domain.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategories();
    Category findCategoryByCode(String code);
}
