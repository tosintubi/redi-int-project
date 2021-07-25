package org.redi.bookservice.registrations.repository;

import org.redi.bookservice.registrations.model.BookRegistrations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRegistrationsRepository extends JpaRepository <BookRegistrations, Long> {
}
