package org.redi.bookservice.registrations.controller;

import lombok.AllArgsConstructor;
import org.redi.bookservice.operations.model.Book;
import org.redi.bookservice.registrations.model.BookRegistrations;
import org.redi.bookservice.registrations.service.BookRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booksregis/")
@AllArgsConstructor
public class BookRegistrationsController {

    private final BookRegistrationService bookRegistrationService;

    @GetMapping("test/")
    public String findByIsbn(){
        return "it works";
    }

    @PostMapping("add/")
    public ResponseEntity<BookRegistrations> saveBookRegistrations(@RequestBody BookRegistrations book){
        BookRegistrations newBookRegis =  bookRegistrationService.saveBookRegistration(book);
        return  new ResponseEntity<>(newBookRegis, HttpStatus.CREATED);
    }

    @PutMapping("disable/{RegBookingId}")
    public  ResponseEntity<BookRegistrations> disableBookRegistrations(@PathVariable("RegBookingId")Long regBookingId){

    }
}
