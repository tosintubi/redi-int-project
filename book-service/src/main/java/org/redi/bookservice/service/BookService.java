package org.redi.bookservice.service;

import lombok.AllArgsConstructor;
import org.redi.bookservice.model.Book;
import org.redi.bookservice.model.BookCatalogue;
import org.redi.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class BookService {



    @Autowired
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

    public List<Book> findBookByTitle(String keyword) {
        if (keyword != null) {
            return bookRepository.findBookByTitleOrOriginalTitle(keyword);
        }
        // returns all the book, if keyword is empty
        return bookRepository.findAll();
    }

    public List<Book> findBookByAuthors(String authors) {
        if (authors != null) {
            return bookRepository.findBookByAuthors(authors);
        }
        // returns all the book, if keyword is empty
        return bookRepository.findAll();
    }
}
