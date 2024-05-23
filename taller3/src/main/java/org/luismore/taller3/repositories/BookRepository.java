package org.luismore.taller3.repositories;

import org.luismore.taller3.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID>{
    Optional<Book> findByIsbn(String isbn);
    void deleteByIsbn(String isbn);
}
