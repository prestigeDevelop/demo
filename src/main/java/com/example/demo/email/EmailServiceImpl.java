package com.example.demo.email;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Primary
//@Component("emailServiceImpl")
@Service
public class EmailServiceImpl implements EmailService{
    @Override
    public void sendEmail(String to, String token) {
        String url="http://localhost:8081/api/v1/registration/userConfirmed/"+token;
        System.out.println("click this link to confirm activate your account: "+url);
    }
}
