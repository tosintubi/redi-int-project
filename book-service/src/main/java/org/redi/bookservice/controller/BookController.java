package org.redi.bookservice.controller;


import org.redi.bookservice.model.Book;
import org.redi.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books/")
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("add/")
    public ResponseEntity<Book> saveBook(@RequestBody Book nbook){
        Book newBook =  bookService.saveBook(nbook);
        return  new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @GetMapping({"all","/"})
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> bookList = bookService.findAllBooks();
        return  new ResponseEntity<>(bookList, HttpStatus.OK);
    }
}