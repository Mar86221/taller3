package org.luismore.taller3.services;

import org.luismore.taller3.domain.dtos.SaveBookDTO;
import org.luismore.taller3.domain.entities.Book;
import org.luismore.taller3.domain.entities.Category;

import java.util.List;

public interface BookService {
  void  save(SaveBookDTO info, Category category);
  List<Book> findAll();
  Book findByIsbn(String isbn);
  void deleteByIsbn(String isbn);
}
