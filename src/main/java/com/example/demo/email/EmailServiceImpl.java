package com.example.demo.email;

import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
    @Override
    public void sendEmail(String to, String token) {
        String url="http://localhost:8080/api/v1/registration/"+token;
        System.out.println(url);
    }
}
