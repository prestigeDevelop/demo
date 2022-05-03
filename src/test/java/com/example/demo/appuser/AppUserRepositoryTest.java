package com.example.demo.appuser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AppUserRepositoryTest {

    @Autowired private AppUserRepository appUserRepository;
    private AppUser appUser=null;

    @BeforeEach
    void setUp() {
        appUser=new AppUser();
        appUser.setAppUserRole(AppUserRole.ADMIN);
        appUser.setPassword("12345");
        appUser.setLastName("Test");
        appUser.setEmail("test@email.com");
        appUser.setFirstName("test");
        appUser=appUserRepository.save(appUser);

    }

    @Test
    void findByEmail() {
        assertEquals(appUserRepository.findByEmailAndName("test@email.com","test").get(),appUser);
        assertEquals(appUserRepository.findByEmail("test@email.com").get(),appUser);
    }

    @Test
    void findByEmailNotFound() {

        NoSuchElementException thrown =  assertThrows(NoSuchElementException.class, () -> {
            appUserRepository.findByEmail("notFound@email.com").get();
        });
        assertEquals("No value present", thrown.getMessage());
    }
}