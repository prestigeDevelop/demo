package com.example.demo.email;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("emailServiceImpl2")
public class EmailServiceImpl2 implements EmailService{
    @Override
    public void sendEmail(String to, String token) {
        String url="http://localhost:8081/api/v1/registration/userConfirmed/"+token;
        System.out.println("click this link to confirm activate your account: "+url);
    }

}
