package com.example.demo.appuser;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly=true)

public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    Optional<AppUser> findByEmail(String email);

    @Query("SELECT u FROM AppUser u WHERE u.email = ?1 and u.firstName=?2")
    Optional<AppUser> findByEmailAndName(String email,String firstName);
}
