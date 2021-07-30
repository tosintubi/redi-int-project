package org.redi.bookservice.registrations.service;

import lombok.extern.slf4j.Slf4j;
import org.redi.bookservice.exceptions.BookRegistrationNotFoundException;
import org.redi.bookservice.exceptions.UserNotFoundException;
import org.redi.bookservice.registrations.model.BookRegistrations;
import org.redi.bookservice.registrations.repository.BookRegistrationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Slf4j
public class BookRegistrationService {

    @Autowired
    private BookRegistrationsRepository bookRegistrationsRepository;


    //
    public BookRegistrations getBookRegistrationByIdAndUserId(Long id, Long userId){
        log.info("Invoking Service: BookService.saveBookRegistration");
        // Long bookId = Long.parseLong(String.valueOf(id));

        return bookRegistrationsRepository.findBookRegistrationsByIdAndUserId(id, userId);

        /*        .orElseThrow(
                ()-> new BookRegistrationNotFoundException("Book registration with Id " + id + " could not be found")
        );*/
    }
    public BookRegistrations saveBookRegistration(BookRegistrations bookRegis){
        log.info("Invoking Service: BookService.saveBookRegistration");

        // userId is not included in payload
        if (bookRegis.getUserId()==null)
            throw new UserNotFoundException("Invalid User, Please check the UserId and try again later");

        int count = bookRegis.getAmount();
        if (count < 1) bookRegis.setEnabled(false);


        bookRegis.setCreated(LocalDateTime.now());
        // Initially set the updated to the created_date
        ;
        bookRegis.setUpdated(LocalDateTime.now());

        bookRegis.setAmountLoanedOut(0);
        return bookRegistrationsRepository.save(bookRegis);
    }

    public BookRegistrations disableBookRegistration(Long bookRegisId, Long userId){
        log.info("Implementing Service: BookService.disableBookRegistration");

        BookRegistrations bookRegs = getBookRegistrationByIdAndUserId(bookRegisId, userId);
        bookRegs.setEnabled(false);
        bookRegs.setUpdated(LocalDateTime.now());
        return bookRegistrationsRepository.save(bookRegs);
    }
}
