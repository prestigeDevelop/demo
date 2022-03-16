package com.example.demo.registration.token;

import com.example.demo.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {

    @Query("SELECT t FROM Token t WHERE t.expires>t.created")
    Optional<Token> findByToken(String token);
    Optional<Token> findByAppUser(AppUser appUser);
}
