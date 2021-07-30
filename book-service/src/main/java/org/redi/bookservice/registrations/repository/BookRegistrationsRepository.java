package org.redi.bookservice.registrations.repository;

import org.redi.bookservice.registrations.model.BookRegistrations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRegistrationsRepository extends JpaRepository <BookRegistrations, Long> {
    // BookRegistrations findByIdAndUserId(Long id, Long userId);



    // @Query(value = "SELECT b from BookRegistrations b WHERE b.Id=:Id AND b.userId=:userId")
    @Query(value = "SELECT b from BookRegistrations b WHERE b.Id=:Id")
    BookRegistrations findBookRegistrationsByIdAndUserId(@Param("Id")Long id);
}
