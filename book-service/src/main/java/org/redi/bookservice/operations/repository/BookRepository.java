package org.redi.bookservice.operations.repository;


import org.redi.bookservice.operations.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long> {

    @Query(value = "SELECT b from Book b WHERE b.authors LIKE %:authors% ")
    List<Book> findBookByAuthors(String authors);

    Book findByIsbn(String isbn);


    @Query(value = "SELECT b from Book b WHERE b.title LIKE %:keyword% OR " +
            "b.originalTitle LIKE %:keyword% OR b.authors LIKE %:keyword% ")
    List<Book> search(@Param("keyword") String keyword);



    // TODO:  Sort and Pagination


}
