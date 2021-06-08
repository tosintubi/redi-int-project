package org.redi.bookservice.service;

import org.redi.bookservice.model.Book;
import org.redi.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {


    @Autowired
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book){
        // log.info("Implementing Service: BookService.saveBook");
        return bookRepository.save(book);
    }


    public List<Book> findAllBooks() {
        return  bookRepository.findAll();
    }
}
