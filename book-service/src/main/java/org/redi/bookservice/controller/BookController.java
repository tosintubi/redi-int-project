package org.redi.bookservice.controller;


import lombok.AllArgsConstructor;
import org.redi.bookservice.model.Book;
import org.redi.bookservice.model.BookCatalogue;
import org.redi.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books/")
@AllArgsConstructor
public class BookController {


    private final BookService bookService;

    
    @PostMapping("add/")
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        Book newBook =  bookService.saveBook(book);
        return  new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @GetMapping({"all","/"})
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> bookList = bookService.findAllBooks();
        return  new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping("search/isbn/{isbn}")
    public ResponseEntity<Book> findByIsbn(@PathVariable String isbn){
        Book book = bookService.findByIsbn(isbn);
        return  new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("search/title/{keyword}")
    public  ResponseEntity<List<Book>> findBookByTitle(@PathVariable("keyword") String keyword){
        List<Book> bookList = bookService.findBookByTitle(keyword);
        return  new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping("search/author/{authors}")
    public  ResponseEntity<List<Book>> findBookByAuthors(@PathVariable("authors") String keyword){
        List<Book> bookList = bookService.findBookByAuthors(keyword);
        return  new ResponseEntity<>(bookList, HttpStatus.OK);
    }
}
