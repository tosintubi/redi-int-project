package org.redi.bookservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String isbn;

    private String bookTitle;
    private String languageCode;

    //    @ManyToMany()
    //    private List<Author> authors;
    private String authors;

    private String publication;
    private String publisher;
    private String category;
    private String language;

    private String isbn13;

    public Book(String isbn, String bookTitle, String languageCode, String authors,
                String publication, String publisher, String category, String language,
                 String isbn13, String authors1) {

        this.bookTitle = bookTitle;
        this.languageCode = languageCode;
        this.authors = authors;
        this.publication = publication;
        this.publisher = publisher;
        this.category = category;
        this.language = language;
        this.isbn = isbn;
        this.isbn13 = isbn13;
        this.authors = authors1;
    }
}
