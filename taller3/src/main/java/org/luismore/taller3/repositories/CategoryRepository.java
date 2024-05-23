package org.luismore.taller3.repositories;

import org.luismore.taller3.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
    // El esquema de datos y el tipo de ID
}
