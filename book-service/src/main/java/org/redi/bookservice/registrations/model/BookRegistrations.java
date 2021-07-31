package org.redi.bookservice.registrations.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.redi.bookservice.operations.model.Book;
import org.redi.bookservice.operations.model.VO.User;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookRegistrations {
    @Id
    @SequenceGenerator(
            name = "book_registrations_seq",
            sequenceName = "book_registrations_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_registrations_seq"
    )
    private Long Id;


    /* Many Book Registrations can be owned linked to one Book*/
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "bookid",
            referencedColumnName = "Id"
    )
    private Book book;


    private Long userId; // Book Owner is a user .. // Many to One

    private LocalDateTime created;
    private LocalDateTime updated;
    private Boolean enabled;
    private Integer amount;
    private Integer amountLoanedOut;
    private Double loanPrice;
}
