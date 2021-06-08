package org.redi.bookservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ISBN;
    private String bookTitle;

    @ManyToMany()
//    private List<Author> authors;
    private String authors;

    private String publicationDate;
    private String publisher;
    private String category;
    private String language;


    public Book() {
    }

    public Book(String ISBN, String bookTitle, String authors,
                String publicationDate, String publisher,
                String category, String language) {
        this.ISBN = ISBN;
        this.bookTitle = bookTitle;
        this.authors = authors;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.category = category;
        this.language = language;
    }


}
