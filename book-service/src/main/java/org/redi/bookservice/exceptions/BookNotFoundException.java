package org.redi.bookservice.exceptions;

public class BookNotFoundException  extends RuntimeException{
    public BookNotFoundException(String message) {
        super(message);
    }
}
