package com.example.demo.security;

import com.example.demo.registration.EmailValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class PasswordEncoder  {

    @Bean
     public BCryptPasswordEncoder bCryptPasswordEncoder(){
         return new BCryptPasswordEncoder();
     }

}
