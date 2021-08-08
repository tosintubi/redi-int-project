package org.redi.userservice.appuser.repository;

import org.redi.userservice.appuser.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);


    @Query("Select u.id, u.firstName, u.lastName, u.phoneNumber, u.email from AppUser u where u.id = ?1")
    AppUser getUserBasicInfoById(Long id);

    @Query("Select u.id, u.firstName, u.lastName, u.phoneNumber, u.email from AppUser u where u.email = ?1")
    AppUser getUserBasicInfoByEmail(String email);
}

