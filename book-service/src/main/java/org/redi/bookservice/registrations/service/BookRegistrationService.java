package org.redi.bookservice.registrations.service;

import lombok.extern.slf4j.Slf4j;
import org.redi.bookservice.exceptions.BookNotFoundException;
import org.redi.bookservice.exceptions.BookRegistrationNotFoundException;
import org.redi.bookservice.exceptions.UserNotFoundException;
import org.redi.bookservice.operations.model.Book;
import org.redi.bookservice.operations.service.BookService;
import org.redi.bookservice.registrations.model.BookRegistrations;
import org.redi.bookservice.registrations.repository.BookRegistrationsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Slf4j
public class BookRegistrationService {

    @Autowired
    private BookRegistrationsRepository bookRegistrationsRepository;

    @Autowired
    private BookService bookService;

    //
    public BookRegistrations getBookRegistrationById(Long id){
        log.info("Invoking Service: BookService.saveBookRegistration");
         Long bookId = Long.parseLong(String.valueOf(id));

         return bookRegistrationsRepository.findById(id).orElseThrow(
                ()-> new BookRegistrationNotFoundException("Book registration with Id " + id + " could not be found")
        );
    }
    public BookRegistrations saveBookRegistration(BookRegistrations bookRegis, String isbn){
        log.info("Invoking Service: BookService.saveBookRegistration");

        Book bookWithIsbn = bookService.findByIsbn(isbn);
        if (bookWithIsbn.getIsbn()==null){
               throw new BookNotFoundException("Book with ISBN \" + id + \" could not be found");
        } else
        {
            // userId is not included in payload
            if (bookRegis.getUserId()==null)
                throw new UserNotFoundException("Invalid User, Please check the UserId and try again later");

            int count = bookRegis.getAmount();
            if (count < 1) bookRegis.setEnabled(false);

            bookRegis.setCreated(LocalDateTime.now());
            // Initially set the updated to the created_date

            bookRegis.setUpdated(LocalDateTime.now());

            bookRegis.setAmountLoanedOut(0);

            bookRegis.setBook(bookWithIsbn);
            return bookRegistrationsRepository.save(bookRegis);
        }
    }

    public BookRegistrations disableBookRegistration(Long bookRegisId){
        log.info("Implementing Service: BookService.disableBookRegistration");

        BookRegistrations bookRegs = getBookRegistrationById(bookRegisId);
        bookRegs.setEnabled(false);
        bookRegs.setUpdated(LocalDateTime.now());

        return bookRegistrationsRepository.save(bookRegs);
    }


    // TODO: Uncomment and fix
    /*public List<BookBookRegistrationReponseTemplateVO> getBookWithBookings(String title){
        BookBookRegistrationReponseTemplateVO vo = new BookBookRegistrationReponseTemplateVO();
        List<Book> book = bookService.findBookByTitle(title);
        for (Book bk:book) {

        }
    }*/
}
