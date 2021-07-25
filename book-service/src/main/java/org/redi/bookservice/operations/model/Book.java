package org.redi.bookservice.operations.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
//@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String isbn;
    private String isbn13;
    private String title;
    @Column(name = "originaltitle")
    private String originalTitle;

    //    @ManyToMany()
    //    private List<Author> authors;
    private String authors;

    @Column(name = "languagecode")
    private String languageCode;

    @Column(name = "publicationyear")
    private Integer publicationYear;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "category")
    private String category;

    @Column(name = "imageurl")
    private String imageUrl;

    @Column(name = "smallimageurl")
    private String smallImageUrl;
}
