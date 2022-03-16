package com.example.demo.appuser;

import com.example.demo.email.EmailService;
import com.example.demo.registration.token.Token;
import com.example.demo.registration.token.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final TokenService tokenService;
    private @Autowired AppUserRepository userRepository;
    @Autowired
    private EmailService emailService;

    private static final String USER_NOT_FOUND="User with email %s not Found";
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).
                orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
    }

    public  AppUser signUpUser(AppUser appUser) throws IllegalStateException{
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if(userRepository.findByEmail(appUser.getEmail()).isPresent()){
            throw new IllegalStateException(String.format("User with email %s already exist",appUser.getEmail()));
        }
        AppUser result=userRepository.save(appUser);

        String tokenValue = UUID.randomUUID().toString();
        Token token=new Token();
        token.setToken(tokenValue);
        token.setAppUser(appUser);
        token.setCreated(LocalDateTime.now());
        token.setExpires(LocalDateTime.now().plusMinutes(1));
        tokenService.saveToken(token);
        emailService.sendEmail(result.getEmail(),tokenValue);
        return  result;
    }

}
