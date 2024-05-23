package org.luismore.taller3.services.implementations;

import org.luismore.taller3.domain.dtos.SaveBookDTO;
import org.luismore.taller3.domain.entities.Book;
import org.luismore.taller3.domain.entities.Category;
import org.luismore.taller3.repositories.BookRepository;
import org.luismore.taller3.services.BookService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //Ahora ya lo puede ocupar el entityManager para poder inyectarlo
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void save(SaveBookDTO info, Category category) {
        Book book = this.findByIsbn(info.getIsbn());

        if (book == null) {
            book = new Book();
        }

        book.setIsbn(info.getIsbn());
        book.setTitle(info.getTitle());
        book.setCategory(category);
        bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElse(null);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteByIsbn(String isbn) {
        Book book = this.findByIsbn(isbn);

        if (book != null) {
            bookRepository.deleteByIsbn(isbn);
        }
    }
}
