package org.redi.bookservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class BookCatalogue {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ISBN;
    private String bookTitle;
    private String originalTitle;
    private int copies;

//    @ManyToMany()
//    private List<Author> authors;
    private String authors;

    private int publicationYear;
    private String publisher;
    private String category;
    private String language;


    public BookCatalogue() {
    }

    public BookCatalogue(String ISBN, String bookTitle, String authors,
                int publicationYear, String publisher,
                String category, String language) {
        this.ISBN = ISBN;
        this.bookTitle = bookTitle;
        this.authors = authors;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.category = category;
        this.language = language;
    }

}
