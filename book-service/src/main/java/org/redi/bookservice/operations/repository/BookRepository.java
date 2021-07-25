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


    @Query(value = "SELECT b from Book b WHERE b.title LIKE %:keyword% OR b.originalTitle LIKE %:keyword% ")
    List<Book> findBookByTitleOrOriginalTitle(@Param("keyword") String keyword);

    // TODO: Implement Sort


}

//
//    @Query("SELECT u FROM User u WHERE u.status = :status and u.name = :name")
//    User findUserByUserStatusAndUserName(@Param("status") Integer userStatus,
//                                         @Param("name") String userName);