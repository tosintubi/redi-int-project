package org.redi.loanservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan {

    @Id
    @SequenceGenerator(
            name = "loan_sequence",
            sequenceName = "loan_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "loan_sequence"
    )
    private Long loanId;

    private Long lender;
    private Long borrower;
    private Book book;
    private boolean returned;
    private double amount;
    private LocalDateTime loanDate;
    private LocalDateTime returnedDate;
    private boolean pickedUp;
    private int numberOfReminders;
}
