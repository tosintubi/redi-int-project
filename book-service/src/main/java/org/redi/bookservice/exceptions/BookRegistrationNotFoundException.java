package org.redi.bookservice.exceptions;

public class BookRegistrationNotFoundException extends RuntimeException {
    public BookRegistrationNotFoundException(String message) {
        super(message);
    }
}
