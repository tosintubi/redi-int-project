package org.redi.bookservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// @Entity
@Getter
@Setter
@NoArgsConstructor

public class BookCatalogue {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String isbn;
    private String isbn13;
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
    private String imageUrl;
    private String smallImageUrl;


    public BookCatalogue(String isbn, String isbn13, String bookTitle, String
            originalTitle, int copies, String authors, int publicationYear, String publisher,
                         String category, String language, String imageUrl, String smallImageUrl) {
        this.isbn = isbn;
        this.isbn13 = isbn13;
        this.bookTitle = bookTitle;
        this.originalTitle = originalTitle;
        this.copies = copies;
        this.authors = authors;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.category = category;
        this.language = language;
        this.imageUrl = imageUrl;
        this.smallImageUrl = smallImageUrl;
    }
}
