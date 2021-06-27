package org.redi.bookservice.repository;


import org.redi.bookservice.model.Author;
import org.redi.bookservice.model.Book;
import org.redi.bookservice.model.BookCatalogue;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long> {

    List<Book> findBookByAuthors(String author);
    Book findByIsbn(String isbn);


    @Query(value = "SELECT b from Book b WHERE CONCAT(b.title, b.originalTitle) LIKE %?1% ")
    List<Book> findBookByTitleOrOriginalTitle(String search);
}
