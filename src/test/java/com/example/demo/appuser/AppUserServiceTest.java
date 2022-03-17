package com.example.demo.appuser;

import com.example.demo.email.EmailService;
import com.example.demo.registration.token.Token;
import com.example.demo.registration.token.TokenService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@RunWith(MockitoJUnitRunner.class)
@WithMockUser
@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    @InjectMocks
    private AppUserService appUserService;

    @Mock
    private AppUserRepository userRepository;
    @Mock
    private  TokenService tokenService;
    @Mock
    private EmailService emailService;
    @BeforeAll
    public  void init(){

    }
    @Test
    @WithMockUser(roles={"USER","ADMIN"},username="test@email.com")
    void loadUserByUsername() {
        when(userRepository.findByEmail(any(String.class)))
                .thenReturn(getAppUser());

        UserDetails user=appUserService.loadUserByUsername("test@email.com");
        assertEquals(user.getUsername(),"test@email.com");
    }

    @Test
    void signUpUser() {
//        when(userRepository.findByEmail(any(String.class)))
//                .thenReturn(Optional.empty());
        when(userRepository.save(any(AppUser.class)))
                .thenReturn(getAppUser().orElse(null));

       // doNothing().when(tokenService).saveToken(getToken());
       // doNothing().when(emailService).sendEmail(any(String.class),any(String.class));

        appUserService.signUpUser(getNewAppUser().orElseThrow(IllegalStateException::new));

    }

    private  Optional<AppUser> getAppUser(){
        AppUser userDetails=new AppUser() ;
        userDetails.setId(123L);
        userDetails.setEmail("test@email.com");
        userDetails.setFirstName("testUser");
        return Optional.of(userDetails);
    }

    private  Optional<AppUser> getNewAppUser(){
        AppUser userDetails=new AppUser() ;
        userDetails.setId(123L);
        userDetails.setEmail("test1234@email.com");
        userDetails.setFirstName("testUser");
        return Optional.of(userDetails);
    }

    private Token getToken(){
        return Token.builder().
                token("fa1a926d-6747-4fba-962b-6f42c16784b9").appUser(getNewAppUser().orElse(null)).id(123456L)
                .build();
    }
}