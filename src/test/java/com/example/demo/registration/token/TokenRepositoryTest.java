package com.example.demo.registration.token;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.appuser.AppUserRole;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TokenRepositoryTest {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired private TokenRepository tokenRepository;
    private AppUser appUser=null;
    private Token token=null;
    @BeforeEach
    void setUp() {
        appUser=new AppUser();
        appUser.setAppUserRole(AppUserRole.ADMIN);
        appUser.setPassword("12345");
        appUser.setLastName("Test");
        appUser.setEmail("test@email.com");
        appUser.setFirstName("test");
        appUser=appUserRepository.save(appUser);

        token=new Token();
        token.setToken("1111111111");
        token.setAppUser(appUser);
        token.setExpires(LocalDateTime.now().plusMinutes(15));
        token.setCreated(LocalDateTime.now());
        tokenRepository.save(token);
    }

    @Test
    public void findByToken(){
      Token savedToken=  tokenRepository.findByToken("1111111111").get();
      assertEquals(savedToken,token);
    }

    @Test
    public void findByTokenExpired(){
        token.setExpires(LocalDateTime.now().minusMinutes(1));
        token.setCreated(LocalDateTime.now());
        tokenRepository.save(token);
        NoSuchElementException ex=assertThrows(NoSuchElementException.class,()->{
            tokenRepository.findByToken("1111111111").get();
        });
    }

    @Test
    public void findByAppUser(){
        Token savedToken=  tokenRepository.findByAppUser(appUser).get();
        assertEquals(savedToken,token);
    }

    @Test
    public void findByAppUserNotFound(){
        NoSuchElementException ex=assertThrows(NoSuchElementException.class,()->{
            appUser=new AppUser();
            appUser.setId(13333331l);
            tokenRepository.findByAppUser(appUser).get();
        });
        assertEquals("No value present", ex.getMessage());
    }
}