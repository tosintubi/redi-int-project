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
    private String isbn13;
    private String title;
    private String originalTitle;

    //    @ManyToMany()
    //    private List<Author> authors;
    private String authors;
    private String languageCode;

    private Integer publicationYear;
    private String publisher;
    private String category;
}
