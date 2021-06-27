package org.redi.bookservice.service;

import lombok.AllArgsConstructor;
import org.redi.bookservice.model.Book;
import org.redi.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class BookService {



    private final BookRepository bookRepository;


    public Book saveBook(Book book){
        // log.info("Implementing Service: BookService.saveBook");
        return bookRepository.save(book);
    }


    public List<Book> findAllBooks() {
        return  bookRepository.findAll();
    }

    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
}
