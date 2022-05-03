package com.example.demo.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DateUtils {

    @Bean
    public LocalDateTime getTime(){
        return LocalDateTime.now();
    }
}
