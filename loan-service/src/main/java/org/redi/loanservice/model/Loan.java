package org.redi.loanservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    @SequenceGenerator(
            name = "loan_seq",
            sequenceName = "loan_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "loan_seq"
    )
    private Long id;

    @Embedded
    private User lender;
    private User borrower;
    private Book book;
    private boolean returned;
    private double amt;
    private LocalDateTime loanDate;
    private LocalDateTime returnedDate;
    private boolean pickedUp;
    private int reminders;
}
